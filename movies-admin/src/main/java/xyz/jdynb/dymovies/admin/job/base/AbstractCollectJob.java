package xyz.jdynb.dymovies.admin.job.base;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.micrometer.common.lang.Nullable;
import jakarta.annotation.Resource;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.quartz.*;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriComponentsBuilder;
import xyz.jdynb.dymovies.admin.pojo.CollectData;
import xyz.jdynb.dymovies.admin.pojo.CollectJsonData;
import xyz.jdynb.dymovies.admin.pojo.CollectXmlData;
import xyz.jdynb.dymovies.admin.service.AdminVodTypeService;
import xyz.jdynb.dymovies.admin.utils.RSSUtils;
import xyz.jdynb.dymovies.common.entity.VodType;
import xyz.jdynb.dymovies.common.utils.IpUtil;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * 采集基础类
 */
@Slf4j
public abstract class AbstractCollectJob implements Job {

    private static final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/131.0.0.0 Safari/537.36 Edg/131.0.0.0";

    /**
     * 传递参数 host 采集站点
     */
    public static final String JOB_KEY_URL = "url";

    /**
     * 无界资源(比较特殊，分页有问题，需要特殊处理)
     */
    private static final String WJM3U8 = "wjm3u8";

    /**
     * 每页数据条数(已知无法修改)
     */
    private static final int PAGE_SIZE = 20;

    private static final int MAX_RETRY_TIMES = 3;

    /**
     * 用于格式化时间
     */
    protected static final DateTimeFormatter formatter = DateTimeFormatter
            .ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     * 最大允许页数（page 大于此值不进行判断是否存在，直接添加）
     */
    protected static final int MAX_ALLOWED_PAGE_COUNT = 100;

    /**
     * 唯一标识
     */
    @Getter
    private String flag;

    /**
     * 影片类型统计进行处理
     */
    @Resource
    private AdminVodTypeService vodTypeService;

    @Resource
    private ObjectMapper objectMapper;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        // 获取到传递的数据
        JobDataMap jobDataMap = jobExecutionContext.getJobDetail().getJobDataMap();
        // 获取 url 为不同的源的请求接口
        String url = jobDataMap.getString(JOB_KEY_URL);
        String group = jobExecutionContext.getJobDetail().getKey().getGroup();
        flag = group;

        log.info("[{}][{}] 开始采集...", group, getJobType().name);
        long start = System.currentTimeMillis();

        try {
            CollectData collectData = collectData(url, Integer.MAX_VALUE);

            // 添加影片分类数据
            if (getJobType() == CollectJobType.LIST) {
                addVodTypes(collectData, group);
                log.info("[{}][{}] 采集类型完成...", group, getJobType().name);
                // LIST 弃用，不进行采集了，全部整合到 DETAIL 中
                return;
            }
            initData(collectData, group);

            // 远程的最后一页
            int remotePageCount = collectData.getPageCount();
            localRecordCount = getLocalRecordCount(group);

            int page;
            if (localRecordCount <= PAGE_SIZE) {
                page = remotePageCount - 1;
            } else {
                page = remotePageCount - (int) Math.floor((double) localRecordCount / PAGE_SIZE);
            }
            if (WJM3U8.equals(group)) {
                // wjm3u8 采集的页数比其他源多一页
                page -= 1;
            }
            log.info("[{}][{}] startPage: {}, localRecordCount: {}, remotePageCount: {}",
                    group, getJobType().name, page, localRecordCount, remotePageCount);
            int collectedTotal = 0;
            // 达到第一页结束采集
            JobKey jobKey = jobExecutionContext.getJobDetail().getKey();
            Scheduler scheduler = jobExecutionContext.getScheduler();
            TriggerKey triggerKey = TriggerKey.triggerKey(jobKey.getName(), jobKey.getGroup());
            int retryTimes = 0;
            while (page > 0) {
                if (!scheduler.isStarted() || scheduler.isShutdown()
                        || scheduler.getTriggerState(triggerKey) == Trigger.TriggerState.PAUSED) {
                    // 自动停止任务
                    log.info("[{}][{}] 暂停: 任务手动暂停", group, getJobType().name);
                    break;
                }
                CollectData currentCollectData;
                try {
                    currentCollectData = collectData(url, page);//RSSUtils.getRssElement(getUrl(url, page));
                } catch (IOException | RuntimeException e) {
                    log.warn("[{}][{}][{}] 采集超时: 正在休眠中... 原因: {}", group, getJobType().name, page, e.getMessage());
                    // 可能出现的采集超时的现象，这里进行休眠120s处理
                    Thread.sleep(120 * 1000);
                    if (retryTimes++ > MAX_RETRY_TIMES) {
                        log.info("[{}][{}][{}] 到达重试最大次数: {}", group, getJobType().name, page, e.getMessage());
                        break;
                    }
                    // 进入下个循环重新请求
                    continue;
                }
                retryTimes = 0;
                int currentPage = page;
                page--;
                // 去除判断页面数据为空
                // 这里已采集的数量计算可能有问题
                // collectedTotal += currentCollectData.getPageSize();
                long collectStart = System.currentTimeMillis();
                // 已采集的数量
                int collectSize = collect(currentCollectData, currentPage);
                if (collectSize == 0) {
                    log.info("[{}][{}][{}] 当前页为空，已自动跳过", group, getJobType().name, currentPage);
                    continue;
                }
                collectedTotal += collectSize;
                log.info("[{}][{}][{}] 添加数据耗时: {}ms", group, getJobType().name, currentPage,
                        System.currentTimeMillis() - collectStart);
            }

            log.info("[{}][{}][{}] 采集结束, 耗时: {}ms, 已采集数据: {} 条", group, getJobType().name,
                    page + 1, System.currentTimeMillis() - start, collectedTotal);
        } catch (Exception e) {
            log.error("[{}][{}] 采集出错: {}", group, getJobType().name, e.toString());
        }
    }

    /**
     * 添加影片类型
     *
     * @param data  xml 数据
     * @param group 组
     */
    private void addVodTypes(CollectData data, String group) {
        if (data instanceof CollectXmlData xmlData) {
            Element classEl = xmlData.getClassEl();
            Elements ty = classEl.getElementsByTag("ty");
            List<VodType> vodTypes = ty
                    .stream()
                    .map(element -> {
                        Integer id = Integer.parseInt(element.attr("id"));
                        return new VodType(id, null, element.text(), group);
                    }).toList();

            if (vodTypeService.countByFlag(group) == 0) {
                vodTypeService.addBatch(vodTypes);
                log.info("[{}] 添加分类成功", group);
            }
        } else if (data instanceof CollectJsonData jsonData) {
            List<CollectJsonData.VodType> types = jsonData.getTypes();
            List<VodType> list = types.stream().map(vodType -> new VodType(vodType.getId(), vodType.getPid(), vodType.getName(), group)).toList();
            if (vodTypeService.countByFlag(group) == 0) {
                vodTypeService.addBatch(list);
                log.info("[{}] 添加分类成功", group);
            }
        }
    }

    private CollectData collectData(String url, int page) throws IOException, RuntimeException {
        log.info("[{}] 采集地址: {}", getJobType().name, url);
        String collectUrl = getUrl(url, page);
        return getCollectData(collectUrl, page);
    }

    /**
     * 本地已采集的数量
     */
    protected int localRecordCount = 0;

    /**
     * 获取本地数据库中采集的数量
     *
     * @param flag 标识
     * @return 数量
     */
    protected abstract int getLocalRecordCount(String flag);

    /**
     * 获取采集类型
     *
     * @return @href CollectType
     */
    protected abstract CollectType getCollectType();

    /**
     * 获取采集类型
     *
     * @return 具体类型
     */
    protected abstract CollectJobType getJobType();

    /**
     * 获取采集地址
     *
     * @param url  地址
     * @param page 页码
     * @return 完成采集地址
     */
    protected String getUrl(String url, int page) {
        UriBuilder u = UriComponentsBuilder.fromUriString(url)
                .queryParam("ac", getJobType().name)
                .queryParam("pg", page);
        return u.toUriString();
    }

    @Nullable
    public final String getElementValue(Element element, String key) {
        Elements elements = element.getElementsByTag(key);
        if (elements.isEmpty()) {
            return null;
        }
        return elements.get(0).text();
    }

    public <T> T fromJson(String url, Class<T> clazz) throws RuntimeException {
        String ip = IpUtil.getRandomChinaIP();
        try (HttpResponse response = HttpRequest.get(url)
                .timeout(60000)
                .header("Accept", RSSUtils.ACCEPT)
                .header("X-Forwarded-For", ip)
                .header("X-Real-IP", ip)
                .header("X-Forwarded-For", ip)
                .header("HTTP_X_FORWARDED_FOR", ip)
                .header("HTTP_CLIENT_IP", ip)
                .header("REMOTE_ADDR", ip)
                .header("X-Real-IP", ip)
                .header("X-Originating-IP", ip)
                .header("Proxy-Client-IP", ip)
                .header("X-Remote-IP", ip)
                .header("WL-Proxy-Client-IP", ip)
                .header("User-Agent", USER_AGENT)
                .execute()) {
            String body = response.body();
            return objectMapper.readValue(body, clazz);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 采集数据
     *
     * @param data 采集到的数据
     */
    protected abstract int collect(CollectData data, int page);

    protected CollectData getCollectData(String collectUrl, int page) throws IOException, RuntimeException {
        CollectType collectType = getCollectType();
        return switch (collectType) {
            case XML -> RSSUtils.getXmlData(collectUrl);
            case JSON -> fromJson(collectUrl, CollectJsonData.class);
            default -> throw new IllegalStateException("collect type " + collectType + " not supported");
        };
    }

    protected void initData(CollectData data, String group) {
    }
}

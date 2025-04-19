package xyz.jdynb.dymovies.job;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.select.Elements;
import xyz.jdynb.dymovies.entity.Vod;
import xyz.jdynb.dymovies.job.base.AbstractCollectJob;
import xyz.jdynb.dymovies.job.base.CollectType;
import xyz.jdynb.dymovies.job.base.CollectJobType;
import xyz.jdynb.dymovies.pojo.CollectData;
import xyz.jdynb.dymovies.pojo.CollectXmlData;
import xyz.jdynb.dymovies.service.VodService;
import xyz.jdynb.dymovies.service.VodTypeService;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 弃用了，使用CollectJsonVodJob
 */
@Deprecated
@Slf4j
public class CollectVodJob extends AbstractCollectJob {

    /**
     * 类型列表
     */
    // protected final Map<Integer, String> typeList = new HashMap<>();

    protected final Map<Integer, Integer> typeMap = new HashMap<>();

    @Resource
    private VodService vodService;

    @Resource
    private VodTypeService vodTypeService;

    @Override
    protected int getLocalRecordCount(String flag) {
        return vodService.count(flag);
    }

    @Override
    protected CollectType getCollectType() {
        return CollectType.XML;
    }

    @Override
    protected CollectJobType getJobType() {
        return CollectJobType.LIST;
    }

    @Override
    protected void initData(CollectData data, String group) {

    }

    @Override
    public int collect(CollectData data, int page) {
        Elements videos = ((CollectXmlData) data).getData();
        if (videos.isEmpty()) {
            return 0;
        }
        List<Vod> vodList = videos.stream().map(element -> {
            Vod vod = new Vod();
            vod.setVid(Integer.parseInt(element.getElementsByTag("id").get(0).text()));
            vod.setTid(Integer.parseInt(element.getElementsByTag("tid").get(0).text()));
            String last = element.getElementsByTag("last").get(0).text();
            LocalDateTime updateTime = LocalDateTime.parse(last, formatter);
            vod.setUpdateTime(updateTime);
            vod.setName(element.getElementsByTag("name").get(0).text());
            vod.setNote(element.getElementsByTag("note").get(0).text());
            vod.setFlag(getFlag());
            // setVodCate(vod);
            return vod;
        }).toList();
        addData(vodList, page);
        return videos.size();
    }

    public final void addData(List<Vod> vodList, int page) {
        if (localRecordCount == 0 || page > MAX_ALLOWED_PAGE_COUNT) {
            vodService.addBatch(vodList);
        } else {
            vodList.forEach(vod -> {
                if (vodService.countByVidAndFlag(vod.getVid(), vod.getFlag()) == 0) {
                    vodService.add(vod);
                }
            });
        }
    }

    /**
     * 设置分类
     * @param vod 影片对象
     */
    public void setVodCate(Vod vod) {
        if (typeMap.isEmpty()) {
            return;
        }
        Integer tid = vod.getTid();
        Integer cid = typeMap.get(tid);
        // vod.setCid(cid);
    }
}

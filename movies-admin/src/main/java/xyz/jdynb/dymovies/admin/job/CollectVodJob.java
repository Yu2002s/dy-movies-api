package xyz.jdynb.dymovies.admin.job;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.select.Elements;
import xyz.jdynb.dymovies.admin.job.base.AbstractCollectJob;
import xyz.jdynb.dymovies.admin.job.base.CollectJobType;
import xyz.jdynb.dymovies.admin.job.base.CollectType;
import xyz.jdynb.dymovies.admin.pojo.CollectData;
import xyz.jdynb.dymovies.admin.pojo.CollectXmlData;
import xyz.jdynb.dymovies.admin.service.AdminVodService;
import xyz.jdynb.dymovies.common.entity.Vod;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 弃用了，使用CollectJsonVodJob
 */
@Deprecated
@Slf4j
public class CollectVodJob extends AbstractCollectJob {

    @Resource
    private AdminVodService vodService;

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
}

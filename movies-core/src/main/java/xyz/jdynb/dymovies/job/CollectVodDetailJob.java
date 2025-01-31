package xyz.jdynb.dymovies.job;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.transaction.annotation.Transactional;
import xyz.jdynb.dymovies.entity.VodDetail;
import xyz.jdynb.dymovies.entity.VodVideo;
import xyz.jdynb.dymovies.job.base.AbstractCollectJob;
import xyz.jdynb.dymovies.job.base.CollectType;
import xyz.jdynb.dymovies.job.base.CollectJobType;
import xyz.jdynb.dymovies.pojo.CollectData;
import xyz.jdynb.dymovies.pojo.CollectXmlData;
import xyz.jdynb.dymovies.service.VodDetailService;
import xyz.jdynb.dymovies.service.VodVideoService;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

@Slf4j
public class CollectVodDetailJob extends AbstractCollectJob {

    @Resource
    private VodDetailService vodDetailService;

    @Resource
    private VodVideoService vodVideoService;

    @Override
    protected CollectJobType getJobType() {
        return CollectJobType.DETAIL;
    }

    @Override
    protected CollectType getCollectType() {
        return CollectType.XML;
    }

    @Override
    protected int getLocalRecordCount(String flag) {
        return vodDetailService.countFlag(flag);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int collect(CollectData data, int page) {
        Elements videos = ((CollectXmlData) data).getData();
        if (videos.isEmpty()) {
            return 0;
        }
        List<VodDetail> vodDetails = videos.stream().map(element -> {
            VodDetail vodDetail = new VodDetail();
            vodDetail.setPic(getElementValue(element, "pic"));
            vodDetail.setVid(Integer.parseInt(element.getElementsByTag("id").get(0).text()));
            String year = element.getElementsByTag("year").get(0).text();
            vodDetail.setYear(year);
            vodDetail.setDirector(element.getElementsByTag("director").get(0).text());

            Element ddEl = element.selectFirst("dl dd");
            if (ddEl != null) {
                // vodDetail.setFlag(ddEl.attr("flag"));
                vodDetail.setFlag(getFlag());
                // 影片视频
                String text = ddEl.text();
                vodDetail.setVideos(parseVodVideo(text, vodDetail.getVid(), vodDetail.getFlag()));
            }

            vodDetail.setDes(element.getElementsByTag("des").get(0).text());
            vodDetail.setActor(element.getElementsByTag("actor").get(0).text());
            vodDetail.setLang(element.getElementsByTag("lang").get(0).text());
            vodDetail.setArea(element.getElementsByTag("area").get(0).text());
            return vodDetail;
        }).toList();
        addData(vodDetails, page);
        return videos.size();
    }

    /**
     * 添加数据到数据库
     * @param vodDetails 添加数据到数据库
     * @param page 页码
     */
    public final void addData(List<VodDetail> vodDetails, int page) {
        // 这里判断只有数据为0（初始化时）或 page 大于（最大允许页数）时进行批量添加，不判断数据是否存在
        if (localRecordCount == 0 || page > MAX_ALLOWED_PAGE_COUNT) {
            vodDetailService.addBatch(vodDetails);
            // 遍历添加视频信息
            vodDetails.forEach(vodDetail -> vodVideoService.addBatch(vodDetail.getVideos()));
        } else {
            vodDetails.forEach(vodDetail -> {
                if (vodDetailService.countByVidAndFlag(vodDetail.getVid(), vodDetail.getFlag()) == 0) {
                    vodDetailService.add(vodDetail);
                    // 遍历添加视频信息
                    vodVideoService.addBatch(vodDetail.getVideos());
                }
            });
        }
    }

    public final List<VodVideo> parseVodVideo(String videosUrl, Integer vid, String flag) {
        String[] v = videosUrl.split("#");
        return Arrays.stream(v).map(new Function<String, VodVideo>() {

            private int index = 1;

            @Override
            public VodVideo apply(String s) {
                String[] strings = s.split("\\$");
                VodVideo vodVideo = new VodVideo();
                if (strings.length == 1) {
                    vodVideo.setName(String.valueOf(index));
                    vodVideo.setUrl(strings[0]);
                } else {
                    vodVideo.setName(strings[0]);
                    vodVideo.setUrl(strings[1]);
                }
                index++;
                vodVideo.setVid(vid);
                vodVideo.setFlag(flag);
                return vodVideo;
            }
        }).toList();
    }
}

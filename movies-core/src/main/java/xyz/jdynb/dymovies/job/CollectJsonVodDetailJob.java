package xyz.jdynb.dymovies.job;

import xyz.jdynb.dymovies.entity.VodDetail;
import xyz.jdynb.dymovies.job.base.CollectType;
import xyz.jdynb.dymovies.pojo.CollectData;
import xyz.jdynb.dymovies.pojo.CollectJsonData;

import java.util.List;

public class CollectJsonVodDetailJob extends CollectVodDetailJob {

    @Override
    protected CollectType getCollectType() {
        return CollectType.JSON;
    }

    @Override
    public int collect(CollectData data, int page) {
        List<CollectJsonData.Vod> jsonData = ((CollectJsonData) data).getList();
        if (jsonData == null || jsonData.isEmpty()) {
            return 0;
        }
        List<VodDetail> vodDetails = jsonData.stream().map(vod -> {
            VodDetail vodDetail = new VodDetail();
            vodDetail.setVid(vod.getVodId());
            vodDetail.setDes(vod.getDesc());
            vodDetail.setArea(vod.getArea());
            vodDetail.setYear(vod.getYear());
            // vodDetail.setFlag(vod.getFlag());
            vodDetail.setFlag(getFlag());
            vodDetail.setLang(vod.getLang());
            vodDetail.setPic(vod.getPic());
            vodDetail.setActor(vod.getActor());
            vodDetail.setDirector(vod.getDirector());
            vodDetail.setName(vod.getVodName());
            vodDetail.setDuration(vod.getDuration());
            vodDetail.setHits(vod.getHits());
            vodDetail.setScore(vod.getScore());
            vodDetail.setVideos(parseVodVideo(vod.getVideos(), vodDetail.getVid(), vodDetail.getFlag()));
            return vodDetail;
        }).toList();
        addData(vodDetails, page);
        return vodDetails.size();
    }
}

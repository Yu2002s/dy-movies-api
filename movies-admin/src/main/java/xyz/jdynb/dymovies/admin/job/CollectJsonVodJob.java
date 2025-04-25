package xyz.jdynb.dymovies.admin.job;

import lombok.extern.slf4j.Slf4j;
import xyz.jdynb.dymovies.admin.job.base.CollectType;
import xyz.jdynb.dymovies.admin.pojo.CollectData;
import xyz.jdynb.dymovies.admin.pojo.CollectJsonData;
import xyz.jdynb.dymovies.common.entity.Vod;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 弃用了。全部改用 DETAIL 类型采集全部影片数据
 */
@Deprecated
@Slf4j
public class CollectJsonVodJob extends CollectVodJob {

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
        List<Vod> vodList = jsonData.stream().map(vod -> {
            Vod v = new Vod();
            v.setName(vod.getVodName());
            v.setVid(vod.getVodId());
            v.setTid(vod.getTypeId());
            v.setUpdateTime(LocalDateTime.parse(vod.getTime(), formatter));
            v.setNote(vod.getRemark());
            v.setFlag(getFlag());
            // setVodCate(v);
            return v;
        }).toList();
        addData(vodList, page);
        return vodList.size();
    }
}

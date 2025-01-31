package xyz.jdynb.dymovies.job;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import xyz.jdynb.dymovies.entity.Vod;
import xyz.jdynb.dymovies.job.base.CollectType;
import xyz.jdynb.dymovies.pojo.CollectData;
import xyz.jdynb.dymovies.pojo.CollectJsonData;

import java.time.LocalDateTime;
import java.util.List;

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
            return v;
        }).toList();
        addData(vodList, page);
        return vodList.size();
    }
}

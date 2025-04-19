package xyz.jdynb.dymovies.service;

import xyz.jdynb.dymovies.dto.VodLatestQueryParamsDto;
import xyz.jdynb.dymovies.dto.VodQueryParamsDto;
import xyz.jdynb.dymovies.entity.Vod;
import xyz.jdynb.dymovies.dto.Page;
import xyz.jdynb.dymovies.entity.VodDetail;

import java.util.List;
import java.util.Map;

public interface VodService {

    int countByVidAndFlag(Integer id, String flag);

    int add(Vod vod);

    int addBatch(List<Vod> vodList);

    int addOrUpdate(Vod vod);

    int count(String flag);

    Page<Vod> findListByType(VodQueryParamsDto vodQueryParamsDto);

    List<Vod> findLast(int pageSize);

    List<Vod> findLast(int pageSize, Integer typeId);

    List<Vod> findLast(VodLatestQueryParamsDto vodLatestQueryParamsDto);
}

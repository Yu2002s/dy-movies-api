package xyz.jdynb.dymovies.service;

import xyz.jdynb.dymovies.common.dto.Page;
import xyz.jdynb.dymovies.common.dto.VodLatestQueryParamsDto;
import xyz.jdynb.dymovies.common.dto.VodQueryParamsDto;
import xyz.jdynb.dymovies.common.entity.Vod;

import java.util.List;

public interface VodService {

    int addOrUpdate(Vod vod);

    int count(String flag);

    Page<Vod> findListByType(VodQueryParamsDto vodQueryParamsDto);

    List<Vod> findLast(int pageSize);

    List<Vod> findLast(int pageSize, Integer typeId);

    List<Vod> findLast(VodLatestQueryParamsDto vodLatestQueryParamsDto);

    Page<Vod> findList(VodQueryParamsDto vodQueryParamsDto);

    int count(VodQueryParamsDto vodQueryParamsDto);

    Integer findVid(Integer id, String flag);
}

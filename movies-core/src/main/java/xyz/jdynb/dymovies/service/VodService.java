package xyz.jdynb.dymovies.service;

import xyz.jdynb.dymovies.dto.VodQueryParamsDto;
import xyz.jdynb.dymovies.entity.Vod;
import xyz.jdynb.dymovies.dto.Page;

import java.util.List;

public interface VodService {

    int countByVidAndFlag(Integer id, String flag);

    int add(Vod vod);

    int addBatch(List<Vod> vodList);

    int addOrUpdate(Vod vod);

    int count(String flag);

    Page<Vod> findListByType(VodQueryParamsDto vodQueryParamsDto);

}

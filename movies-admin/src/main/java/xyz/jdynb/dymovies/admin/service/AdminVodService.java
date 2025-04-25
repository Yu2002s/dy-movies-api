package xyz.jdynb.dymovies.admin.service;

import xyz.jdynb.dymovies.common.dto.Page;
import xyz.jdynb.dymovies.common.dto.VodQueryParamsDto;
import xyz.jdynb.dymovies.common.entity.Vod;

import java.util.List;

public interface AdminVodService {

    int count(String flag);

    int count(VodQueryParamsDto vodQueryParamsDto);

    Page<Vod> findList(VodQueryParamsDto queryParamsDto);

    int countByVidAndFlag(Integer id, String flag);

    int addBatch(List<Vod> vodList);

    int add(Vod vod);
}

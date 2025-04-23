package xyz.jdynb.dymovies.service;

import xyz.jdynb.dymovies.dto.VodQueryParamsDto;
import xyz.jdynb.dymovies.entity.Vod;
import xyz.jdynb.dymovies.entity.VodDetail;
import xyz.jdynb.dymovies.dto.Page;

public interface VodSearchService {

    int countByKeywordAndType(VodQueryParamsDto vodQueryParamsDto);

    Page<VodDetail> findListByKeywordAndType(VodQueryParamsDto vodQueryParamsDto);
}

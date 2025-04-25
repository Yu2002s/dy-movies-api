package xyz.jdynb.dymovies.service;

import xyz.jdynb.dymovies.common.dto.Page;
import xyz.jdynb.dymovies.common.dto.VodQueryParamsDto;
import xyz.jdynb.dymovies.common.entity.VodDetail;

public interface VodSearchService {

    Page<VodDetail> findList(VodQueryParamsDto vodQueryParamsDto);
}

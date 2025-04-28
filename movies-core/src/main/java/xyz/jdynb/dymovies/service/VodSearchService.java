package xyz.jdynb.dymovies.service;

import xyz.jdynb.dymovies.common.dto.Page;
import xyz.jdynb.dymovies.common.dto.VodQueryParamsDto;
import xyz.jdynb.dymovies.common.entity.VodDetail;

import java.util.List;

public interface VodSearchService {

    Page<VodDetail> findList(VodQueryParamsDto vodQueryParamsDto);

    List<String> findNameByKeyword(String keyword);
}

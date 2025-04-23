package xyz.jdynb.dymovies.service.admin;

import xyz.jdynb.dymovies.dto.Page;
import xyz.jdynb.dymovies.dto.VodQueryParamsDto;
import xyz.jdynb.dymovies.entity.Vod;

public interface AdminVodService {

    int count(VodQueryParamsDto vodQueryParamsDto);

    Page<Vod> findList(VodQueryParamsDto queryParamsDto);

}

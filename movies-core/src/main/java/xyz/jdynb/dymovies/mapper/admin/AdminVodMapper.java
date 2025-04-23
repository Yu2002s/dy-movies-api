package xyz.jdynb.dymovies.mapper.admin;

import org.apache.ibatis.annotations.Mapper;
import xyz.jdynb.dymovies.dto.VodQueryParamsDto;
import xyz.jdynb.dymovies.entity.Vod;

import java.util.List;

@Mapper
public interface AdminVodMapper {

    int count(VodQueryParamsDto vodQueryParamsDto);

    List<Vod> findList(VodQueryParamsDto vodQueryParamsDto);

}

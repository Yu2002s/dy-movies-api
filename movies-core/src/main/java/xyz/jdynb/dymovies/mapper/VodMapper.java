package xyz.jdynb.dymovies.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import xyz.jdynb.dymovies.dto.VodQueryParamsDto;
import xyz.jdynb.dymovies.entity.Vod;

import java.util.List;

/**
* @author Administrator
* @description 针对表【vod(影片数据)】的数据库操作Mapper
* @createDate 2025-01-07 11:06:49
* @Entity xyz.jdynb.dymovies.entity.Vod
*/
@Mapper
public interface VodMapper {

    int add(Vod vod);

    int addBatch(List<Vod> vodList);

    int countByVidAndFlag(Integer vid, String flag);

    int countByTidAndFlag(Integer tid, String flag);

    @Select("select count(id) from dy_movies.vod where flag = #{flag}")
    int count(String flag);

    List<Vod> findListByTid(VodQueryParamsDto vodQueryParamsDto);
}





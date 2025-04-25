package xyz.jdynb.dymovies.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import xyz.jdynb.dymovies.common.dto.VodLatestQueryParamsDto;
import xyz.jdynb.dymovies.common.dto.VodQueryParamsDto;
import xyz.jdynb.dymovies.common.entity.Vod;

import java.util.List;

/**
* @author Administrator
* @description 针对表【vod(影片数据)】的数据库操作Mapper
* @createDate 2025-01-07 11:06:49
 * @Entity xyz.jdynb.dymovies.common.entity.Vod
*/
@Mapper
public interface VodMapper {

    int countByVidAndFlag(Integer vid, String flag);

    int countByTidAndFlag(Integer tid, String flag);

    int countByPidAndFlag(Integer pid, String flag);

    List<Vod> findList(VodQueryParamsDto vodQueryParamsDto);

    int count(VodQueryParamsDto vodQueryParamsDto);

    @Select("select count(id) from dy_movies.vod where flag = #{flag}")
    int countByFlag(String flag);

    List<Vod> findListByTid(VodQueryParamsDto vodQueryParamsDto);

    int updateAllCate();

    List<Vod> findLast(VodLatestQueryParamsDto params);

    Integer findVid(Integer id, String flag);
}





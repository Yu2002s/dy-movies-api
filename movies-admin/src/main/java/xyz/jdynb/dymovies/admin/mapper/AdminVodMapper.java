package xyz.jdynb.dymovies.admin.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import xyz.jdynb.dymovies.common.dto.VodQueryParamsDto;
import xyz.jdynb.dymovies.common.entity.Vod;

import java.util.List;

@Mapper
public interface AdminVodMapper {

    int count(VodQueryParamsDto vodQueryParamsDto);

    @Select("select count(id) from dy_movies.vod where flag = #{flag}")
    int countByFlag(String flag);

    List<Vod> findList(VodQueryParamsDto vodQueryParamsDto);

    int add(Vod vod);

    int addBatch(List<Vod> vodList);

    int countByVidAndFlag(Integer vid, String flag);
}

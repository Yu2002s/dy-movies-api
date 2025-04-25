package xyz.jdynb.dymovies.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import xyz.jdynb.dymovies.common.entity.VodProvider;

import java.util.List;

/**
* @author Administrator
* @description 针对表【vod_provider(影片供应商)】的数据库操作Mapper
* @createDate 2025-01-07 20:03:41
 * @Entity xyz.jdynb.dymovies.common.entity.VodProvider
*/
@Mapper
public interface VodProviderMapper {

    @Select("select id, name, remark from dy_movies.vod_provider order by weight desc")
    List<VodProvider> findAll();
}





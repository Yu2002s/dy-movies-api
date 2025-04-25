package xyz.jdynb.dymovies.mapper;

import org.apache.ibatis.annotations.Mapper;
import xyz.jdynb.dymovies.common.entity.VodDetail;

/**
* @author Yu2002s
* @description 针对表【vod_detail(影片详情表)】的数据库操作Mapper
* @createDate 2025-01-07 11:08:25
 * @Entity xyz.jdynb.dymovies.common.entity.VodDetail
*/
@Mapper
public interface VodDetailMapper {

    VodDetail findById(Integer id);
}





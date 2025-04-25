package xyz.jdynb.dymovies.admin.mapper;

import org.apache.ibatis.annotations.Mapper;

/**
* @author Administrator
* @description 针对表【vod_config】的数据库操作Mapper
* @createDate 2025-01-11 20:21:43
* @Entity xyz.jdynb.dymovies.entity.VodConfig
*/
@Mapper
public interface AdminVodConfigMapper {

    String findFlag();

    int updateFlag(String flag);
}





package xyz.jdynb.dymovies.mapper;

import org.apache.ibatis.annotations.Mapper;
import xyz.jdynb.dymovies.common.entity.TvLive;

import java.util.List;

/**
* @author Administrator
* @description 针对表【tv_live(电视直播)】的数据库操作Mapper
* @createDate 2025-01-15 19:12:03
* @Entity xyz.jdynb.dymovies.common.entity.TvLive
*/
@Mapper
public interface TvLiveMapper {

    List<TvLive> findList();
}





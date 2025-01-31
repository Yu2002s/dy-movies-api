package xyz.jdynb.dymovies.mapper;

import org.apache.ibatis.annotations.Mapper;
import xyz.jdynb.dymovies.entity.VodVideo;

import java.util.List;

/**
* @author Administrator
* @description 针对表【vod_video(影片视频信息)】的数据库操作Mapper
* @createDate 2025-01-07 11:08:44
* @Entity xyz.jdynb.dymovies.entity.VodVideo
*/
@Mapper
public interface VodVideoMapper {

    int countByVid(Integer vid);

    int countByVidAndFlag(Integer vid, String flag);

    int addBatch(List<VodVideo> vodVideos);

    void add(VodVideo vodVideo);

    List<VodVideo> findListByName(String name);

    VodVideo findById(Integer id);

    void updateUrlById(Integer id, String url);
}





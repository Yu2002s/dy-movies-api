package xyz.jdynb.dymovies.mapper;

import org.apache.ibatis.annotations.Mapper;
import xyz.jdynb.dymovies.common.entity.VodVideo;

import java.util.List;

/**
* @author Administrator
* @description 针对表【vod_video(影片视频信息)】的数据库操作Mapper
* @createDate 2025-01-07 11:08:44
 * @Entity xyz.jdynb.dymovies.common.entity.VodVideo
*/
@Mapper
public interface VodVideoMapper {

    void createTable(String flag);

    int existTable(String flag);

    int countByVid(Integer vid);

    int countByVidAndFlag(Integer vid, String flag);

    List<VodVideo> findListByName(String name);

    List<VodVideo> findListByNameAndFlag(String name, String flag);

    VodVideo findById(Integer id, String flag);

    List<VodVideo> findByVid(Integer vid, String flag);

    void updateUrlById(Integer id, String url, String flag);
}





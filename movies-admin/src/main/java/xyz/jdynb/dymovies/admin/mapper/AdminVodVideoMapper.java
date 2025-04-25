package xyz.jdynb.dymovies.admin.mapper;

import org.apache.ibatis.annotations.Mapper;
import xyz.jdynb.dymovies.common.entity.VodVideo;

import java.util.List;

@Mapper
public interface AdminVodVideoMapper {

    void createTable(String flag);

    int existTable(String flag);

    int countByVid(Integer vid);

    int countByVidAndFlag(Integer vid, String flag);

    int addBatch(List<VodVideo> vodVideos, String flag);

    void add(VodVideo vodVideo, String flag);

}

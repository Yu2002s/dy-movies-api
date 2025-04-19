package xyz.jdynb.dymovies.service;

import xyz.jdynb.dymovies.entity.VodVideo;
import xyz.jdynb.dymovies.pojo.VodSource;
import xyz.jdynb.dymovies.vo.VodSourceVideoVo;

import java.util.List;

public interface VodVideoService {

    void createTable(String flag);

    boolean existTable(String flag);

    int countByVid(Integer vid);

    int countByVidAndFlag(Integer vid, String flag);

    VodVideo findById(Integer id, String flag);

    List<VodVideo> findByVid(Integer vid, String flag);

    int addBatch(List<VodVideo> vodVideos, String flag);

    void add(VodVideo vodVideo, String flag);

    List<VodSource> findSourcesByName(String name);

    void updateUrlById(Integer id, String url, String flag);

    VodSourceVideoVo findSourcesAndVideos(Integer vid, String flag);
}

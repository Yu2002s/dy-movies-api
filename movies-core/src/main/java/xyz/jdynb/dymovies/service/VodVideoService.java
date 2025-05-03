package xyz.jdynb.dymovies.service;

import xyz.jdynb.dymovies.common.entity.VodVideo;
import xyz.jdynb.dymovies.common.pojo.VodSource;
import xyz.jdynb.dymovies.common.vo.VodSourceVideoVo;

import java.io.IOException;
import java.util.List;

public interface VodVideoService {

    void createTable(String flag);

    boolean existTable(String flag);

    VodVideo findById(Integer id, String flag);

    List<VodVideo> findByVid(Integer vid, String flag);

    List<VodSource> findSourcesByName(String name);

    void updateUrlById(Integer id, String url, String flag);

    VodSourceVideoVo findSourcesAndVideos(Integer vid, String flag);

    List<VodVideo> findListByName(String name, String flag);

    void proxy(String url) throws IOException;
}

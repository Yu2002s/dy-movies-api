package xyz.jdynb.dymovies.service;

import xyz.jdynb.dymovies.entity.VodVideo;
import xyz.jdynb.dymovies.pojo.VodSource;

import java.util.List;

public interface VodVideoService {

    int countByVid(Integer vid);

    int countByVidAndFlag(Integer vid, String flag);

    VodVideo findById(Integer id);

    int addBatch(List<VodVideo> vodVideos);

    void add(VodVideo vodVideo);

    List<VodSource> findSourcesByName(String name);

    void updateUrlById(Integer id, String url);
}

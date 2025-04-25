package xyz.jdynb.dymovies.admin.service;

import xyz.jdynb.dymovies.common.entity.VodVideo;

import java.util.List;

public interface AdminVodVideoService {

    void createTable(String flag);

    boolean existTable(String flag);

    int countByVid(Integer vid);

    int countByVidAndFlag(Integer vid, String flag);

    int addBatch(List<VodVideo> vodVideos, String flag);

    void add(VodVideo vodVideo, String flag);
}

package xyz.jdynb.dymovies.admin.service;

import xyz.jdynb.dymovies.common.entity.VodDetail;

import java.util.List;

public interface AdminVodDetailService {

    int countFlag(String flag);

    int countByVidAndFlag(Integer vid, String flag);

    int addBatch(List<VodDetail> vodDetails);

    void add(VodDetail vodDetail);
}

package xyz.jdynb.dymovies.service;

import xyz.jdynb.dymovies.entity.VodDetail;
import xyz.jdynb.dymovies.vo.VodDetailVo;

import java.util.List;

public interface VodDetailService {

    int countFlag(String flag);

    int countByVidAndFlag(Integer vid, String flag);

    int addBatch(List<VodDetail> vodDetails);

    void add(VodDetail vodDetail);

    /**
     * 通过 id 获取影片详情
     * @param id 影片 id
     * @return 影片详情信息
     */
    VodDetail findById(Integer id);
}

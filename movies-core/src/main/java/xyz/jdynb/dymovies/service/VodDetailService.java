package xyz.jdynb.dymovies.service;

import xyz.jdynb.dymovies.common.entity.VodDetail;

public interface VodDetailService {

    /**
     * 通过 id 获取影片详情
     * @param id 影片 id
     * @return 影片详情信息
     */
    VodDetail findById(Integer id);
}

package xyz.jdynb.dymovies.service;

import xyz.jdynb.dymovies.vo.HomeVodDataVo;

public interface HomeVodService {

    /**
     * 获取首页数据
     * @return 首页数据
     */
    HomeVodDataVo getHomeVodData();

    /**
     * 清除首页数据缓存
     */
    void clearHomeDataCache();

}

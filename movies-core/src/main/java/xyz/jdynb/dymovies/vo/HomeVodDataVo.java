package xyz.jdynb.dymovies.vo;

import lombok.Data;
import xyz.jdynb.dymovies.entity.VodBanner;
import xyz.jdynb.dymovies.pojo.VodFeed;

import java.util.List;

@Data
public class HomeVodDataVo {

    /**
     * Banner列表
     */
    private List<VodBanner> banners;

    private List<VodFeed> feeds;
}

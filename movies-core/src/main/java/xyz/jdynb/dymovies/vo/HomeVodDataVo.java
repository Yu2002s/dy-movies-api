package xyz.jdynb.dymovies.vo;

import lombok.Data;
import xyz.jdynb.dymovies.entity.VodBanner;

import java.util.List;

@Data
public class HomeVodDataVo {

    /**
     * Banner列表
     */
    private List<VodBanner> banners;
}

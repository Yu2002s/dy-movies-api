package xyz.jdynb.dymovies.vo;

import lombok.Data;
import xyz.jdynb.dymovies.common.entity.VodBanner;
import xyz.jdynb.dymovies.dto.VodFeedDto;

import java.io.Serializable;
import java.util.List;

@Data
public class HomeVodDataVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Banner 列表
     */
    private List<VodBanner> banners;

    /**
     * Feed 列表
     */
    private List<VodFeedDto> feeds;
}

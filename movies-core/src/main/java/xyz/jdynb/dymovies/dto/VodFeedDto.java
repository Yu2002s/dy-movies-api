package xyz.jdynb.dymovies.dto;

import lombok.Data;
import xyz.jdynb.dymovies.common.entity.Vod;
import xyz.jdynb.dymovies.common.entity.VodType;
import xyz.jdynb.dymovies.enums.HomeCate;

import java.io.Serializable;
import java.util.List;

@Data
public class VodFeedDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private String title;

    private Integer cateId;

    private List<Vod> vodList;

    public static VodFeedDto createFeed(HomeCate homeCate, List<Vod> vodList) {
        VodFeedDto feed = new VodFeedDto();
        feed.setTitle(homeCate.getName());
        feed.setCateId(homeCate.getCateId());
        feed.setVodList(vodList);
        return feed;
    }

    public static VodFeedDto createFeed(VodType vodType, List<Vod> vodList) {
        VodFeedDto feed = new VodFeedDto();
        feed.setTitle(vodType.getName());
        feed.setCateId(vodType.getId());
        feed.setVodList(vodList);
        return feed;
    }

}

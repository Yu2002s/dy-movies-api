package xyz.jdynb.dymovies.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import xyz.jdynb.dymovies.entity.VodProvider;
import xyz.jdynb.dymovies.entity.VodVideo;

import java.util.List;

@AllArgsConstructor
@Data
public class VodSourceVideoVo {

    /**
     * 源列表
     */
    private List<VodProvider> sources;

    /**
     * 视频列表
     */
    private List<VodVideo> videos;
}

package xyz.jdynb.dymovies.common.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import xyz.jdynb.dymovies.common.entity.VodProvider;
import xyz.jdynb.dymovies.common.entity.VodVideo;

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

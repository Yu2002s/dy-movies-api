package xyz.jdynb.dymovies.common.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 影片视频信息
 * @TableName vod_video
 */
@Data
public class VodVideo implements Serializable {
    /**
     * 影片视频唯一id
     */
    private Integer id;

    /**
     * 影片关联id
     */
    private Integer vid;

    /**
     * 视频名称
     */
    private String name;

    /**
     * 视频播放地址
     */
    private String url;

    /**
     * 唯一标识
     */
    private String flag;

    private static final long serialVersionUID = 1L;
}
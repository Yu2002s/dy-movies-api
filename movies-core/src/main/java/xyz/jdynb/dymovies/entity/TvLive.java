package xyz.jdynb.dymovies.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;

/**
 * 电视直播
 * @TableName tv_live
 */
@Data
public class TvLive implements Serializable {
    /**
     * 唯一id
     */
    private Integer id;

    /**
     * 来源名称
     */
    private String name;

    /**
     * 直播地址
     */
    private String url;

    /**
     * 是否启用
     */
    private Integer status;

    /**
     * 权重
     */
    private Integer weight;

    /**
     * 创建时间
     */
    private LocalDateTime createAt;

    private static final long serialVersionUID = 1L;
}
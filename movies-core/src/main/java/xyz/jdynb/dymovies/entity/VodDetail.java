package xyz.jdynb.dymovies.entity;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

import lombok.Data;
import xyz.jdynb.dymovies.pojo.VodSource;

/**
 * 影片详情表
 * @TableName vod_detail
 */
@Data
public class VodDetail {
    /**
     * 影片详情id
     */
    private Integer id;

    /**
     * 影片关联id
     */
    private Integer vid;

    /**
     * 影片名称
     */
    private String name;

    /**
     * 影片图片
     */
    private String pic;

    /**
     * 影片语言
     */
    private String lang;

    /**
     * 影片地区
     */
    private String area;

    /**
     * 影片年份
     */
    private String year;

    /**
     * 影片演员信息
     */
    private String actor;

    /**
     * 影片导演信息
     */
    private String director;

    /**
     * 唯一标识
     */
    private String flag;

    /**
     * 影片详情描述信息
     */
    private String des;

    private String hits;

    private String score;

    private String duration;

    private Integer videoId;

    /**
     * 视频地址
     */
    private String videoUrl;

    /**
     * 采集需要的源列表
     */
    private List<VodVideo> videos;
}
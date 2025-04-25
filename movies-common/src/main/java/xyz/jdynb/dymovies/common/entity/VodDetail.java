package xyz.jdynb.dymovies.common.entity;

import lombok.Data;

import java.util.List;

/**
 * 影片详情表
 *
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
     * 类型 id
     */
    private Integer tid;

    /**
     * 影片类型名称
     */
    private String type;

    /**
     * 备注信息
     */
    private String note;

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

    /**
     * 热度
     */
    private Integer hits;

    /**
     * 评分
     */
    private Float score;

    /**
     * 时长
     */
    private String duration;

    /*
     * 视频 id
     private Integer videoId;
     */

    /*
     * 视频地址
     private String videoUrl;
     */

    /**
     * 更新时间
     */
    private String updateTime;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 采集需要的源列表
     */
    private List<VodVideo> videos;
}
package xyz.jdynb.dymovies.common.entity;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 影片数据
 * @TableName vod
 */
@Data
public class Vod implements Serializable {
    /**
     * 唯一id
     */
    private Integer id;

    /**
     * 影片id
     */
    private Integer vid;

    /**
     * 影片名称
     */
    private String name;

    /**
     * 影片类型id
     */
    private Integer tid;

    /**
     * 影片图片
     */
    private String pic;

    /**
     * 备注信息
     */
    private String note;

    /**
     * 类型
     */
    private String type;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 影片来源标识
     */
    private String flag;

    private static final long serialVersionUID = 1L;
}
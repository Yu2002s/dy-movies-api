package xyz.jdynb.dymovies.entity;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 影片类型表
 * @TableName vod_type
 */
@AllArgsConstructor
@Data
public class VodType implements Serializable {
    /**
     * 影片类型id
     */
    private Integer id;

    /**
     * 类型名称
     */
    private String name;

    /**
     * 标识
     */
    private String flag;

    private static final long serialVersionUID = 1L;
}
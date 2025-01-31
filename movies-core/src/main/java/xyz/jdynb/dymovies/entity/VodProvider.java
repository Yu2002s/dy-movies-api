package xyz.jdynb.dymovies.entity;

import java.io.Serializable;
import lombok.Data;

/**
 * job组列表
 * @TableName job_group
 */
@Data
public class VodProvider implements Serializable {
    /**
     * 唯一id
     */
    private Integer id;

    /**
     * 组名
     */
    private String name;

    /**
     * 备注
     */
    private String remark;

    /**
     * 接口地址
     */
    private String url;

    private static final long serialVersionUID = 1L;
}
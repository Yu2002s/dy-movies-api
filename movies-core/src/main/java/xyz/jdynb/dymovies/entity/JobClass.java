package xyz.jdynb.dymovies.entity;

import java.io.Serializable;
import lombok.Data;

/**
 * job 启动类表
 * @TableName job_class
 */
@Data
public class JobClass implements Serializable {
    /**
     * 唯一id
     */
    private Integer id;

    /**
     * 启动类名称
     */
    private String name;

    /**
     * 备注信息
     */
    private String remark;

    private static final long serialVersionUID = 1L;
}
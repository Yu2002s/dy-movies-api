package xyz.jdynb.dymovies.admin.entity;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;

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
    @NotNull(message = "className不能为空")
    @NotEmpty(message = "className不能为空")
    private String name;

    /**
     * 备注信息
     */
    private String remark;

    private static final long serialVersionUID = 1L;
}
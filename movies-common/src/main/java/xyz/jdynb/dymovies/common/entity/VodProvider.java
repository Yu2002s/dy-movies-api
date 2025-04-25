package xyz.jdynb.dymovies.common.entity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import xyz.jdynb.dymovies.common.validator.UpdateGroup;

import java.io.Serializable;

/**
 * job组列表
 * @TableName job_group
 */
@Data
public class VodProvider implements Serializable {
    /**
     * 唯一id
     */
    @NotNull(message = "id不能为空", groups = UpdateGroup.class)
    private Integer id;

    /**
     * 组名
     */
    @NotNull(message = "采集来源名称不能为空")
    @NotBlank(message = "采集来源名称不能为空")
    private String name;

    /**
     * 备注
     */
    private String remark;

    private static final long serialVersionUID = 1L;
}
package xyz.jdynb.dymovies.common.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    @NotNull(message = "来源名称不能为空")
    @NotBlank(message = "来源名称不能为空")
    private String name;

    /**
     * 直播地址
     */
    @NotNull(message = "直播地址不能为空")
    private String url;

    private static final long serialVersionUID = 1L;
}
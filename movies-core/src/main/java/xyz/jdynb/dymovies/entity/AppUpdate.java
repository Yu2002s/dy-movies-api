package xyz.jdynb.dymovies.entity;

import java.time.LocalDateTime;
import java.util.Date;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import xyz.jdynb.dymovies.validator.UpdateGroup;

/**
 * App 更新表
 * @TableName app_update
 */
@Data
public class AppUpdate {
    /**
     * 唯一id
     */
    @NotNull(groups = UpdateGroup.class)
    private Integer id;

    /**
     * 版本名
     */
    @NotNull
    @NotBlank
    private String versionName;

    /**
     * 版本代码
     */
    @NotNull
    private Integer versionCode;

    /**
     * 更新内容
     */
    @NotNull
    @NotBlank
    private String content;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 是否强制更新
     */
    private Integer isForce;

    /**
     * 更新地址
     */
    @NotNull
    @NotBlank
    private String url;

    /**
     * 更新时间
     */
    private LocalDateTime createAt;
}
package xyz.jdynb.dymovies.entity;

import java.time.LocalDateTime;
import java.util.Date;
import lombok.Data;

/**
 * App 更新表
 * @TableName app_update
 */
@Data
public class AppUpdate {
    /**
     * 唯一id
     */
    private Integer id;

    /**
     * 版本名
     */
    private String versionName;

    /**
     * 版本代码
     */
    private Integer versionCode;

    /**
     * 更新内容
     */
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
    private String url;

    /**
     * 更新时间
     */
    private LocalDateTime createAt;
}
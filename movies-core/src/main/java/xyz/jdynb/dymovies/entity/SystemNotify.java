package xyz.jdynb.dymovies.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import lombok.Data;

/**
 * 系统通知
 * @TableName system_notify
 */
@Data
public class SystemNotify {
    /**
     * 唯一id
     */
    private Integer id;

    /**
     * 通知内容
     */
    private String content;

    /**
     * 创建时间
     */
    private LocalDateTime createAt;

    /**
     * 权重
     */
    private Integer weight;
}
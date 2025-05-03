package xyz.jdynb.dymovies.common.entity;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;

/**
 * 反馈
 * @Table feedback
 */
@Data
public class Feedback {

    /**
     * 唯一 id
     */
    private Integer id;

    /**
     * 反馈内容
     */
    @NotNull(message = "反馈内容不能为空")
    @Length(min = 10, max = 100, message = "反馈内容请大于10字小于100字")
    private String content;

    /**
     * 联系方式
     */
    // @Length(min = 5, max = 30, message = "联系方式请大于5字小于30字")
    private String contact;

    /**
     * 是否已读 （0未读，1已读）
     */
    private int isRead;

    /**
     * 创建时间
     */
    private LocalDateTime createAt;
}

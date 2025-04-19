package xyz.jdynb.dymovies.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 * 添加评论所需的参数
 */
@Data
public class VodCommentAddParamsDto {

    @NotNull(message = "影片id不能为空")
    private Integer detailId;

    /**
     * 指定的评论id，如果为空，则表示顶级评论，其他情况则是评论下的回复
     */
    private Integer commentId;

    /**
     * 来自回复的用户（前端不用传递）
     */
    private Integer fromUid;

    /**
     * 回复给指定用户 id
     */
    private Integer toUid;

    /**
     * 评论内容
     */
    @NotNull(message = "评论内容不能为空")
    @Length(min = 1, max = 200, message = "评论内容长度不能小于1和不能大于200")
    private String content;
}

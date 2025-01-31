package xyz.jdynb.dymovies.entity;

import java.time.LocalDateTime;
import java.util.Date;
import lombok.Data;

/**
 * 影片回复
 * @TableName vod_reply
 */
@Data
public class VodReply {
    /**
     * 唯一id
     */
    private Integer id;

    /**
     * 评论id
     */
    private Integer commentId;

    /**
     * 发表回复的用户id
     */
    private Integer fromUid;

    /**
     * 回复指定用户的id
     */
    private Integer toUid;

    private User fromUser;

    private User toUser;

    /**
     * 回复内容
     */
    private String content;

    /**
     * 创建时间
     */
    private LocalDateTime createAt;

    /**
     * 是否已删除
     */
    private Integer isDelete;
}
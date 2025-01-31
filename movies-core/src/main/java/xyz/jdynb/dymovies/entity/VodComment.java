package xyz.jdynb.dymovies.entity;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import lombok.Data;

/**
 * 影片评论表
 * @TableName vod_comment
 */
@Data
public class VodComment {
    /**
     * 唯一id
     */
    private Integer id;

    /**
     * 用户id
     */
    private Integer uid;

    /**
     * 评论用户
     */
    private User user;

    /**
     * 详情id
     */
    private Integer detailId;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 创建时间
     */
    private LocalDateTime createAt;

    /**
     * 是否被删除
     */
    private Integer isDelete;

    private List<VodReply> replyList;
}
package xyz.jdynb.dymovies.entity;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

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

    /**
     * 回复列表
     */
    private List<VodReply> replyList;
    
    /**
     * 回复数量
     */
    private Integer replyCount;
}
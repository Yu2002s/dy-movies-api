package xyz.jdynb.dymovies.admin.entity;

import lombok.Data;

/**
 * 管理员用户
 * @TableName admin_user
 */
@Data
public class AdminUser {
    /**
     * 唯一id
     */
    private Integer id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 头像
     */
    private String avatar;
}
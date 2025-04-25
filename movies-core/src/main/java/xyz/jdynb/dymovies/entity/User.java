package xyz.jdynb.dymovies.entity;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 用户表
 * @TableName user
 */
@Data
public class User implements Serializable {

    public static final String ID = "id";

    /**
     * 用户id
     */
    private Integer id;

    /**
     * 用户状态
     */
    private Integer status;

    /**
     * 用户名
     */
    private String username;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 密码
     */
    private String password;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 创建时间
     */
    private LocalDateTime createAt;

    /**
     * 更新时间
     */
    private LocalDateTime updateAt;

    /**
     * ip 地址
     */
    private String ipAddr;

    private static final long serialVersionUID = 1L;
}
package xyz.jdynb.dymovies.common.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 
 * @TableName vod_config
 */
@Data
public class VodConfig implements Serializable {
    /**
     * 
     */
    private String flag;

    private static final long serialVersionUID = 1L;
}
package xyz.jdynb.dymovies.entity;

import java.io.Serializable;
import lombok.Data;

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
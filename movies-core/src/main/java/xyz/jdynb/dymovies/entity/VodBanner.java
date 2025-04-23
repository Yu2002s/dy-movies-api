package xyz.jdynb.dymovies.entity;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import xyz.jdynb.dymovies.validator.UpdateGroup;

import java.io.Serializable;

/**
 * 影片轮播 banner
 */
@Data
public class VodBanner implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private Integer vid;

    private String name;

    private String pic;

    private String note;

    private String flag;

    private Integer status;

    private Integer weight;

    // private String des;
}

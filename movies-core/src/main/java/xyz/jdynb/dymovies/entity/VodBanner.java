package xyz.jdynb.dymovies.entity;

import lombok.Data;

/**
 * 影片轮播 banner
 */
@Data
public class VodBanner {

    private Integer id;

    private Integer vid;

    private String name;

    private String pic;

    private String note;

    private String des;
}

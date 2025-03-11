package xyz.jdynb.dymovies.entity;

import lombok.Data;
import xyz.jdynb.dymovies.pojo.VodFilter;

import java.util.List;

/**
 * 影片分类
 * @table vod_cate
 */
@Data
public class VodCate {

    /**
     * 分类id
     */
    private Integer id;

    /**
     * 分类名称
     */
    private String name;

    /**
     * 权重
     */
    private int weight;

    /**
     * 过滤条件列表
     */
    private List<VodFilter> filterList;
}

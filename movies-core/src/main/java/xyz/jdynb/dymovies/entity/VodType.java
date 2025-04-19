package xyz.jdynb.dymovies.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 影片类型表
 * @TableName vod_type
 */
@AllArgsConstructor
@Data
@NoArgsConstructor
public class VodType implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    /**
     * 影片类型 id
     */
    private Integer id;

    /**
     * 父类 id
     */
    private Integer pid;

    /**
     * 类型名称
     */
    private String name;

    /**
     * 标识
     */
    private String flag;

    public VodType(Integer id, Integer pid, String name, String flag) {
        this.id = id;
        this.pid = pid;
        this.name = name;
        this.flag = flag;
    }

    public boolean isChild() {
        return children == null;
    }

    /**
     * 类型子集
     */
    private List<VodType> children = null;
}
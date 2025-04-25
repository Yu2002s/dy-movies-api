package xyz.jdynb.dymovies.common.entity;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import xyz.jdynb.dymovies.common.validator.UpdateGroup;

import java.io.Serial;
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
    
    @Serial
    private static final long serialVersionUID = 1L;
    
    /**
     * 影片类型 id
     */
    @NotNull(groups = UpdateGroup.class)
    private Integer id;

    /**
     * 父类 id
     */
    private Integer pid;

    /**
     * 类型名称
     */
    @NotNull
    private String name;

    /**
     * 标识
     */
    @NotNull
    private String flag;

    private int status;

    private int weight;

    public VodType(Integer id, Integer pid, String name, String flag) {
        this.id = id;
        this.pid = pid;
        this.name = name;
        this.flag = flag;
    }

    /**
     * 类型子集
     */
    private List<VodType> children = null;
}
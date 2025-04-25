package xyz.jdynb.dymovies.common.dto;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.jdynb.dymovies.common.validator.VodSearchGroup;

/**
 * 影片查询所需的一些参数
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class VodQueryParamsDto extends PageParams {

    /**
     * 父类型 id
     */
    @Nullable
    private Integer pid;

    /**
     * 类型id
     */
    private Integer tid;

    /**
     * 标识（采集源标识）
     */
    private String flag;

    /**
     * 查询关键字
     */
    @NotNull(message = "请输入搜索关键字", groups = VodSearchGroup.class)
    @NotBlank(message = "搜索关键字不能为空", groups = VodSearchGroup.class)
    private String keyword;

    /**
     * 排序依赖
     */
    private Integer sort;

    /**
     * 年份
     */
    private String year;

    /**
     * 结束年份
     */
    private String yearEnd;

    /**
     * 地区
     */
    private String area;
}

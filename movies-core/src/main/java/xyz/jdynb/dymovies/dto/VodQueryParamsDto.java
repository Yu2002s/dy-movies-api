package xyz.jdynb.dymovies.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.groups.Default;
import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.jdynb.dymovies.validator.VodSearchGroup;

@EqualsAndHashCode(callSuper = true)
@Data
public class VodQueryParamsDto extends PageParams {

    /**
     * 父类型 id
     */
    private Integer pid;

    /**
     * 类型id
     */
    @NotNull(message = "tid 不能为空", groups = Default.class)
    private Integer tid;

    /**
     * 类型 id 列表
     */
    // private Integer[] tids;

    /**
     * 标识
     */
    private String flag;

    /**
     * 查询关键字
     */
    @NotNull(message = "请输入搜索关键字", groups = VodSearchGroup.class)
    @NotBlank(message = "搜索关键字不能为空", groups = VodSearchGroup.class)
    private String keyword;
}

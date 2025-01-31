package xyz.jdynb.dymovies.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.groups.Default;
import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.jdynb.dymovies.pojo.PageParams;
import xyz.jdynb.dymovies.validator.VodSearchGroup;

@EqualsAndHashCode(callSuper = true)
@Data
public class VodQueryParamsDto extends PageParams {

    /**
     * 类型id
     */
    @NotNull(message = "typeId 不能为空", groups = Default.class)
    private Integer typeId;

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

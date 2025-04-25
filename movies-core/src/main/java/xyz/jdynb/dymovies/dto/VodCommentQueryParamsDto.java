package xyz.jdynb.dymovies.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.jdynb.dymovies.common.dto.PageParams;

@EqualsAndHashCode(callSuper = true)
@Data
public class VodCommentQueryParamsDto extends PageParams {

    @NotNull(message = "详情id不能为空")
    private Integer detailId;

}

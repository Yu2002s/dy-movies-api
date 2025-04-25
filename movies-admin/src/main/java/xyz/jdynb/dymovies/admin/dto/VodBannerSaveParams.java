package xyz.jdynb.dymovies.admin.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import xyz.jdynb.dymovies.common.validator.UpdateGroup;

@Data
public class VodBannerSaveParams {

    @NotNull(groups = UpdateGroup.class)
    private Integer id;

    @NotNull
    private Integer vid;

    private String flag;

    private Integer status;

    private Integer weight;
}

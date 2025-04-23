package xyz.jdynb.dymovies.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import xyz.jdynb.dymovies.validator.UpdateGroup;

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

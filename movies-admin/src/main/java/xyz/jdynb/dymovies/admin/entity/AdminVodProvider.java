package xyz.jdynb.dymovies.admin.entity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.jdynb.dymovies.common.entity.VodProvider;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
public class AdminVodProvider extends VodProvider implements Serializable {

    /**
     * 接口地址
     */
    @NotNull(message = "采集接口地址不能为空")
    @NotBlank(message = "采集接口地址不能为空")
    private String url;

    private Integer weight;

    private static final long serialVersionUID = 1L;
}

package xyz.jdynb.dymovies.admin.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.jdynb.dymovies.common.entity.TvLive;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
public class AdminTvLive extends TvLive {

    /**
     * 是否启用
     */
    private Integer status;

    /**
     * 权重
     */
    private Integer weight;

    /**
     * 创建时间
     */
    private LocalDateTime createAt;
}

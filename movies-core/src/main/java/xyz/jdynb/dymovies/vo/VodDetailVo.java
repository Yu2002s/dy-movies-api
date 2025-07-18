package xyz.jdynb.dymovies.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.jdynb.dymovies.common.entity.VodDetail;

@EqualsAndHashCode(callSuper = true)
@Data
public class VodDetailVo extends VodDetail {

    private String videoUrl;
}

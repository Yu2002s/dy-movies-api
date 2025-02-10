package xyz.jdynb.dymovies.pojo;

import lombok.Data;
import xyz.jdynb.dymovies.entity.VodDetail;

import java.util.List;

@Data
public class VodFeed {

    private String title;

    private String cateId;

    private List<VodDetail> vodList;

}

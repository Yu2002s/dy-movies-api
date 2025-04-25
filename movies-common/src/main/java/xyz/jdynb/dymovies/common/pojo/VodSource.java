package xyz.jdynb.dymovies.common.pojo;

import lombok.Data;
import xyz.jdynb.dymovies.common.entity.VodVideo;

import java.util.List;

@Data
public class VodSource {

    private String name;

    private int count;

    private List<VodVideo> videos;

}

package xyz.jdynb.dymovies.pojo;

import lombok.Data;
import xyz.jdynb.dymovies.entity.VodVideo;

import java.util.List;

@Data
public class VodSource {

    private String name;

    private int count;

    private List<VodVideo> videos;

}

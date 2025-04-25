package xyz.jdynb.dymovies.admin.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
public class CollectJsonData extends CollectData {

    private Integer code;
    private String msg;

    private List<Vod> list;

    @JsonProperty("class")
    private List<VodType> types;

    @Data
    public static class Vod {
        @JsonProperty("vod_id")
        private Integer vodId;
        @JsonProperty("vod_name")
        private String vodName;
        @JsonProperty("type_id")
        private Integer typeId;
        @JsonProperty("vod_remarks")
        private String remark;
        @JsonProperty("vod_time")
        private String time;
        @JsonProperty("vod_play_from")
        private String flag;
        @JsonProperty("vod_pic")
        private String pic;
        @JsonProperty("vod_lang")
        private String lang;
        @JsonProperty("vod_area")
        private String area;
        @JsonProperty("vod_year")
        private String year;
        @JsonProperty("vod_actor")
        private String actor;
        @JsonProperty("vod_director")
        private String director;
        @JsonProperty("vod_hits")
        private Integer hits;
        @JsonProperty("vod_duration")
        private String duration;
        @JsonProperty("vod_score")
        private Float score;
        @JsonProperty("vod_play_url")
        private String videos;
        @JsonProperty("vod_content")
        private String desc;
    }

    @Data
    public static class VodType {
        @JsonProperty("type_id")
        private Integer id;
        @JsonProperty("type_pid")
        private Integer pid;
        @JsonProperty("type_name")
        private String name;
    }
}

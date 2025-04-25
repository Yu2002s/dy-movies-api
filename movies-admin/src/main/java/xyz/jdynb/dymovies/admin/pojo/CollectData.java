package xyz.jdynb.dymovies.admin.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class CollectData {

    private int page;

    @JsonProperty("limit")
    private int pageSize;

    @JsonProperty("pagecount")
    private int pageCount;

    @JsonProperty("total")
    private int recordCount;
}

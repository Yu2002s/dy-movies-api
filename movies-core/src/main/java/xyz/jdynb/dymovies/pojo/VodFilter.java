package xyz.jdynb.dymovies.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import xyz.jdynb.dymovies.config.VodFilterConfig;
import xyz.jdynb.dymovies.entity.VodType;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

/**
 * 影片过滤条件
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class VodFilter {

    public static final int FILTER_NONE = 0;

    public static final int FILTER_TYPE = 1;

    public static final int FILTER_AREA = 2;

    public static final int FILTER_LANG = 3;

    public static final int FILTER_YEAR = 4;

    public static final String[] AREA = {"全部", "大陆", "香港", "韩国", "美国", "日本", "法国", "英国", "德国", "中国台湾", "泰国"};

    public static final String[] LANG = {"全部", "国语", "粤语", "英语", "日语", "韩语", "法语"};

    public static final String[] YEAR = new String[10];

    static {
        LocalDate localDate = LocalDate.now();
        int year = localDate.getYear();
        for (int i = 0; i < 5; i++) {
            YEAR[i] = String.valueOf(year);
        }
        YEAR[5] = "10年代";
        YEAR[6] = "00年代";
        YEAR[7] = "90年代";
        YEAR[8] = "80年代";
        YEAR[9] = "更早";
    }

    private Integer id;

    private String title;

    private String key;

    private List<VodType> types;

    public VodFilter(VodFilterConfig config) {
        this.id = config.getId();
        this.title = config.getTitle();
        this.key = config.getKey();
        this.types = Arrays.stream(config.getValues()).map(s -> new VodType(null, null, s, null)).toList();
    }

}

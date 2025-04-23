package xyz.jdynb.dymovies.enums;

import lombok.Getter;
import xyz.jdynb.dymovies.pojo.VodFilter;

/**
 * 影片筛选配置
 */
@Getter
public enum VodFilterConfig {

    AREA(VodFilter.FILTER_AREA, "地区", "area", VodFilter.AREA),
    YEAR(VodFilter.FILTER_YEAR, "年份", "year", VodFilter.YEAR),
    LANG(VodFilter.FILTER_LANG, "语言", "lang", VodFilter.LANG);

    private final Integer id;

    private final String title;

    private final String key;

    private final String[] values;

    VodFilterConfig(Integer id, String title, String key, String[] values) {
        this.id = id;
        this.title = title;
        this.key = key;
        this.values = values;
    }
}

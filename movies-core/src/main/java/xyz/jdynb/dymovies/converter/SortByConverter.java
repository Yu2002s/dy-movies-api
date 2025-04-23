package xyz.jdynb.dymovies.converter;

import jakarta.annotation.Nullable;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import xyz.jdynb.dymovies.enums.SortBy;

/**
 * SortBy 枚举类型转换
 */
@Component
public class SortByConverter implements Converter<Integer, SortBy> {

    @Override
    public SortBy convert(@Nullable Integer source) {
        if (source == null) {
            return null;
        }
        return SortBy.fromValue(source);
    }
}

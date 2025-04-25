package xyz.jdynb.dymovies.common.dto;

import lombok.Data;

import java.util.List;

@Data
public class Page<T> {

    private int currentPage;

    private int total;

    private int lastPage;

    private int pageSize;

    private List<T> data;

    public static <T> Page<T> of(int currentPage, int total, int pageSize, List<T> data) {
        Page<T> page = new Page<>();
        page.setCurrentPage(currentPage);
        page.setTotal(total);
        page.setPageSize(pageSize);
        page.setLastPage(total / pageSize + 1);
        page.setData(data);
        return page;
    }
}

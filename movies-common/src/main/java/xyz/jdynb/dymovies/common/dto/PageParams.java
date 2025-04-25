package xyz.jdynb.dymovies.common.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

@Data
@NoArgsConstructor
public class PageParams {

    /**
     * 当前页码
     */
    @NotNull(message = "页码不能为空")
    @Min(value = 1, message = "页码最小为1")
    private Integer page = 1;

    /**
     * 每页条数
     */
    @Range(min = 5, max = 40, message = "每页条数范围在1-100之间")
    private Integer pageSize = 10;

    /**
     * 获取偏移量
     */
    private int pageOffset;

    public int getPageOffset() {
        return (page - 1) * pageSize;
    }

    public PageParams(Integer page, Integer pageSize) {
        this.page = page;
        this.pageSize = pageSize;
    }
}

package xyz.jdynb.dymovies.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

@Data
public class PageParams {

    /**
     * 当前页码
     */
    @NotNull(message = "页码不能为空")
    @Min(value = 1, message = "页码最小为1")
    private Integer page;

    /**
     * 每页条数
     */
    @Range(min = 5, max = 40, message = "每页条数范围在1-100之间")
    private Integer pageSize;

    /**
     * 获取偏移量
     */
    private int pageOffset;

    public int getPageOffset() {
        return (page - 1) * pageSize;
    }
}

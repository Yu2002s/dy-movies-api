package xyz.jdynb.dymovies.common.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import xyz.jdynb.dymovies.common.constants.StatusCode;

/**
 * 对返回数据的进一步封装
 * @param <T> 需要返回具体的数据类型
 */
@Data
@AllArgsConstructor
public class Result<T> {

    /**
     * 状态码
     */
    private Integer code;

    /**
     * 描述信息
     */
    private String msg;

    /**
     * 返回具体数据
     */
    private T data;

    public static <T> Result<T> success() {
        return success(null);
    }

    /**
     * 响应成功信息
     *p
     * @param data 具体数据
     * @return 成功实体
     */
    public static <T> Result<T> success(T data) {
        return success(null, data);
    }

    public static <T> Result<T> success(String msg) {
        return success(msg, null);
    }

    /**
     * 响应成功信息
     *
     * @param msg  具体描述信息
     * @param data 具体数据
     * @return 成功实体
     */
    public static <T> Result<T> success(String msg, T data) {
        return new Result<>(StatusCode.SUCCESS, msg, data);
    }

    /**
     * 响应失败信息
     *
     * @param msg 具体失败描述
     * @return 失败实体
     */
    public static <T> Result<T> error(String msg) {
        return error(StatusCode.ERROR, msg);
    }

    public static <T> Result<T> error(Integer code, String msg) {
        return new Result<>(code, msg, null);
    }

}

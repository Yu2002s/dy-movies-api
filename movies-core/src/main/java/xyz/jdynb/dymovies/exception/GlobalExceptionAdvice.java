package xyz.jdynb.dymovies.exception;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import xyz.jdynb.dymovies.common.pojo.Result;

import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 全局异常处理器
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionAdvice {

    /**
     * 方法参数校验失败异常处理
     *
     * @param e       异常对象
     * @param request 请求对象
     * @return result
     */
    // @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result<String> exceptionValidException(MethodArgumentNotValidException e, HttpServletRequest request) {
        printLog(request.getRequestURI(), e.getMessage());
        FieldError fieldError = e.getFieldError();
        if (fieldError == null) {
            return Result.error("请求参数错误");
        }
        return Result.error(fieldError.getField() + ": " + fieldError.getDefaultMessage()
                + " 请求的值: " + fieldError.getRejectedValue());
    }

    // @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public Result<String> exceptionValidException(ConstraintViolationException e, HttpServletRequest request) {
        String error = e.getConstraintViolations()
                .stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.joining(","));
        printLog(request.getRequestURI(), e.getMessage());
        return Result.error(error);
    }

    private void printLog(String uri, String message) {
        log.error("校验错误: {} 发生异常, {}", uri, message);
    }

    /**
     * 捕获全部异常
     *
     * @param e 异常对象
     * @return 错误信息
     */
    // @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public Result<String> doSystemException(Exception e) {
        log.error("全局异常: {}", e.toString());
        return Result.error(e.toString());
    }

}

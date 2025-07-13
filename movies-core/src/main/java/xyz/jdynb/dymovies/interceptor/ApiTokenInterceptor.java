package xyz.jdynb.dymovies.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import xyz.jdynb.dymovies.common.anno.Cacheable;
import xyz.jdynb.dymovies.common.utils.AesEncryption;

import java.lang.reflect.Method;

/**
 * 校验请求合法性
 */
@Slf4j
@Component
@Order(1)
public class ApiTokenInterceptor implements HandlerInterceptor {

    private final AesEncryption aesEncryption;

    @Value("${api-key}")
    private String API_KEY;

    public ApiTokenInterceptor(AesEncryption aesEncryption) {
        this.aesEncryption = aesEncryption;
    }

    @Override
    public boolean preHandle(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler) throws Exception {
        String time = request.getHeader("Time");
        String token = request.getHeader("Api-Token");
        String apiKey = request.getHeader("Api-Key");

        if (handler instanceof HandlerMethod) {
            Method method = ((HandlerMethod) handler).getMethod();
            handleCache(method, request, response);
        }

        // log.info("token verify: {} , {} , {}", time, apiKey, token);
        if (API_KEY.equals(apiKey)) {
            return true;
        }

        if (time == null || token == null) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return false;
        }

        String myToken = aesEncryption.encrypt(API_KEY + time);

        if (!token.equals(myToken)) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return false;
        }

        if (System.currentTimeMillis() - Long.parseLong(time) > 10000) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return false;
        }
        return true;
    }

    /**
     * 对缓存进行处理
     * @param method 请求的方法
     * @param request 请求对象
     * @param response 响应对象
     */
    private void handleCache(Method method, HttpServletRequest request, HttpServletResponse response) {
        Cacheable cacheable = method.getAnnotation(Cacheable.class);
        String platform = request.getHeader("Platform");
        if (cacheable != null) {
            boolean isCache = cacheable.value();
            if (isCache && "android".equals(platform)) {
                response.setHeader("Cache-Control", "max-age=120");
            }
        } else {
            if ("android".equals(platform)) {
                response.setHeader("Cache-Control", "max-age=120");
            }
        }
    }
}

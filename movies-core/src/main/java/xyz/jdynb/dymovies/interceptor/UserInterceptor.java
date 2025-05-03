package xyz.jdynb.dymovies.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import xyz.jdynb.dymovies.common.anno.RequireLogin;
import xyz.jdynb.dymovies.common.constants.StatusCode;
import xyz.jdynb.dymovies.common.pojo.Result;
import xyz.jdynb.dymovies.common.utils.JwtUtils;
import xyz.jdynb.dymovies.entity.User;

import java.io.IOException;
import java.lang.reflect.Method;

@Component
public class UserInterceptor implements HandlerInterceptor {

    @Resource
    private JwtUtils jwtUtils;

    @Override
    public boolean preHandle(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler) throws Exception {
        Method method = ((HandlerMethod) handler).getMethod();
        RequireLogin requireLogin = method.getAnnotation(RequireLogin.class);

        // 如果没有标记必须登录的直接放行
        if (requireLogin == null) {
            return true;
        }

        String token = request.getHeader("Authorization");
        if (token == null) {
            writeJson(response);
            return false;
        }

        Integer userId = jwtUtils.parseToken(token);
        // 判断token是否可以解密
        if (userId == null) {
            writeJson(response);
            return false;
        }
        // 解密成功，添加到请求参数中
        request.setAttribute(User.ID, userId);
        return true;
    }

    private void writeJson(HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setStatus(HttpServletResponse.SC_OK);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(response.getWriter(), Result.error(StatusCode.NOT_LOGIN, "未登录，请先登录"));
    }
}

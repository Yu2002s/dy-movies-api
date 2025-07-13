package xyz.jdynb.dymovies.config;

import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import xyz.jdynb.dymovies.interceptor.ApiTokenInterceptor;
import xyz.jdynb.dymovies.interceptor.UserInterceptor;

/**
 * web配置类
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final ApiTokenInterceptor APITokenInterceptor;

    @Resource
    private UserInterceptor userInterceptor;

    @Value("${spring.profiles.active}")
    private String profile;

    @Value("${api-authentication}")
    private Boolean apiAuth;

    public WebConfig(ApiTokenInterceptor APITokenInterceptor) {
        this.APITokenInterceptor = APITokenInterceptor;
    }

    @Override
    public void addInterceptors(@NonNull InterceptorRegistry registry) {
        if (apiAuth && !"dev".equals(profile)) {
            registry.addInterceptor(APITokenInterceptor)
                    .addPathPatterns("/**")
                    .excludePathPatterns("/admin/**");
        }
        registry.addInterceptor(userInterceptor)
                .addPathPatterns("/admin/**", "/users/**", "/vodComments/**");
    }
}

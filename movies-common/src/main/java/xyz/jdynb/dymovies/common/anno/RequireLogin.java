package xyz.jdynb.dymovies.common.anno;

import java.lang.annotation.*;

/**
 * 配置于 Controller，表面这个路由需要登陆才能进行访问
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RequireLogin {

    boolean value() default true;
}

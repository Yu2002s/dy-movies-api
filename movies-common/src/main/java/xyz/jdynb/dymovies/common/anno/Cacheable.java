package xyz.jdynb.dymovies.common.anno;

import java.lang.annotation.*;

/**
 * 配置于 Controller 是否开启缓存，默认是开启缓存
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Cacheable {

    boolean value() default true;

}

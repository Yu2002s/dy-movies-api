package xyz.jdynb.dymovies.anno;

import java.lang.annotation.*;

@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RequireLogin {

    boolean value() default true;
}

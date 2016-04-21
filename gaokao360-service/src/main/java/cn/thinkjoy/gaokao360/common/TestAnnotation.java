package cn.thinkjoy.gaokao360.common;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * Created by liusven on 16/1/8.
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface TestAnnotation {
}

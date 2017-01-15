package org.springframework.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 请求映射注解
 * @author yxm
 * @date 2017-1-15
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RequestMapper {
   RequestMethod[] method() default RequestMethod.GET;
   String value() default "";
}

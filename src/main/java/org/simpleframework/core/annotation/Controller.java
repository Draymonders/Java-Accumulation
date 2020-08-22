package org.simpleframework.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Description:
 * @Date 2020/08/22 08:53
 * @auther Draymonder
 */
@Target(ElementType.TYPE) // 作用在类上
@Retention(RetentionPolicy.RUNTIME)
public @interface Controller {

}

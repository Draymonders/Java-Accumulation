package cn.draymonder.anonation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Description:
 * @Date 2020/08/21 23:20
 * @auther Draymonder
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface InfoAnnotation {
  // 名字
  public String name() default "Draymonder";
  // 年龄
  public int age() default 20;
  // 性别
  public String sex() default "男";
  // 会的语言
  public String[] language();
}

package demo.anonation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Description:
 * @Date 2020/08/21 23:21
 * @auther Draymonder
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CourseAnnotation {
  // 课程名称
  public String name();
  // 课程标签
  public String tag();
  // 课程简介
  public String profile();
  // 课程编号
  int index() default 100;
}

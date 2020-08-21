package cn.draymonder.anonation;

import cn.draymonder.entity.CourseAnnotation;
import cn.draymonder.entity.InfoAnnotation;
import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @Description: 注解解析
 * @Date 2020/08/21 23:42
 * @auther Draymonder
 */
public class AnnotationParserExec {
  // 类注解
  public static void parseTypeAnnoation() throws ClassNotFoundException {
    System.out.println("类上注解获取");
    Class clazz = Class.forName("cn.draymonder.anonation.MoocCourse");
    CourseAnnotation courseAnnotation = (CourseAnnotation) clazz.getAnnotation(CourseAnnotation.class);
    System.out.println("annotation: " + courseAnnotation);
    String name = courseAnnotation.name();
    String profile = courseAnnotation.profile();
    String tag = courseAnnotation.tag();
    int index = courseAnnotation.index();

    System.out.println(String.format("name: %s, tag: %s, profile: %s, index: %d", name, profile, tag, index));
    System.out.println("=====");
  }

  public static void parseFiledAnnotation() throws ClassNotFoundException {
    System.out.println("字段注解获取");
    Class clazz = Class.forName("cn.draymonder.anonation.MoocCourse");
    for (Field field : clazz.getDeclaredFields()) {
      if (field.isAnnotationPresent(InfoAnnotation.class)) {
        InfoAnnotation infoAnnotation = field.getAnnotation(InfoAnnotation.class);
        System.out.println("annotation: " + infoAnnotation);
        String name = infoAnnotation.name();
        int age = infoAnnotation.age();
        String sex = infoAnnotation.sex();
        String []languages = infoAnnotation.language();
        System.out.println(String.format("name: %s, sex: %s, age: %d", name, sex, age));
        System.out.print("used languages:");
        for (String language : languages) {
          System.out.print(" " + language);
        }
      }
    }
    System.out.println("\n=====");
  }

  public static void parseMethodAnnotation() throws ClassNotFoundException {
    System.out.println("方法注解获取");
    Class clazz = Class.forName("cn.draymonder.anonation.MoocCourse");
    for (Method method : clazz.getDeclaredMethods()) {
      if (method.isAnnotationPresent(CourseAnnotation.class)) {
        CourseAnnotation courseAnnotation = method.getAnnotation(CourseAnnotation.class);
        System.out.println("annotation: " + courseAnnotation);
        String name = courseAnnotation.name();
        String profile = courseAnnotation.profile();
        String tag = courseAnnotation.tag();
        int index = courseAnnotation.index();

        System.out.println(String.format("name: %s, tag: %s, profile: %s, index: %d", name, profile, tag, index));
      }
    }

    System.out.println("=====");
  }

  public static void main(String[] args) throws Exception {
    parseTypeAnnoation();
    parseFiledAnnotation();
    parseMethodAnnotation();
  }
}

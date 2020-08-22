package cn.draymonder.anonation;

/**
 * @Description: 课程实体
 * @Date 2020/08/21 23:30
 * @auther Draymonder
 */
@CourseAnnotation(name = "课程", tag = "编程", profile = "只是一个简单的编程实践课程")
public class MoocCourse {
  @InfoAnnotation(name = "Draymonder", age = 22, sex = "男", language = {"Java", "javascript", "python", "cpp", "go"})
  private String person;

  @CourseAnnotation(name = "注解学习", tag="Java注解", profile = "Java注解学习", index = 233)
  public void doAnnotationCourse() {
    System.out.println("新的注解学习的课程");
  }
}

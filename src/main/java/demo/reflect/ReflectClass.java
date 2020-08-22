package demo.reflect;

public class ReflectClass {

  public static void main(String[] args) throws ClassNotFoundException {
    // 三种方式
    Class clazz1 = ReflectEntity.class;
    Class clazz2 = new ReflectEntity().getClass();
    System.out.println("方式1 & 2 equal: " + (clazz1 == clazz2));

    Class clazz3 = Class.forName("demo.reflect.ReflectEntity");
    System.out.println("方式1 & 3 equal: " + (clazz1 == clazz3));
  }
}

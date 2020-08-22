package demo.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class ReflectConstrucotr {

  public static void main(String[] args)
      throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
    Class<?> clazz = Class.forName("demo.reflect.ReflectEntity");

    Constructor<?>[] constructors = clazz.getConstructors();
    for (Constructor constructor : constructors) {
      System.out.println("constructor: " + constructor);
    }
    System.out.println("-----");

    Constructor<?>[] declaredConstructors = clazz.getDeclaredConstructors();
    for (Constructor declaredConstructor : declaredConstructors) {
      System.out.println("declared constructor: " + declaredConstructor);
    }
    System.out.println("====");

    Constructor constructor = clazz.getDeclaredConstructor(String.class, int.class);
    constructor.setAccessible(true);
    ReflectEntity entity = (ReflectEntity) constructor.newInstance("233", 1);
    System.out.println("构造entity成功");
  }
}

package cn.draymonder.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectMethod {

  public static void main(String[] args)
      throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
    Class<?> clazz = Class.forName("cn.draymonder.reflect.ReflectEntity");
    Constructor constructor = clazz.getDeclaredConstructor(String.class, int.class);
    constructor.setAccessible(true);
    ReflectEntity entity = (ReflectEntity) constructor.newInstance("233", 1);
    System.out.println("构造entity成功");

    for (Method method : clazz.getMethods()) {
      System.out.println("get method: " + method);
    }
    System.out.println("====");
    for (Method method : clazz.getDeclaredMethods()) {
      System.out.println("get declared method: " + method);
    }
    System.out.println("====");

    Method method = clazz.getDeclaredMethod("showName", String.class, int.class);
    System.out.println("get specific method: " + method);
    method.setAccessible(true);

    String name = (String)method.invoke(entity, "Draymonder", 666);
    System.out.println("when exec the method && get result: " + name);
  }
}

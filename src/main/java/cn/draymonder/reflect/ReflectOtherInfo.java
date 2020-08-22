package cn.draymonder.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class ReflectOtherInfo {

  public static void main(String[] args)
      throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
    Class<?> clazz = Class.forName("cn.draymonder.reflect.ReflectEntity");
    Constructor constructor = clazz.getDeclaredConstructor(String.class, int.class);
    constructor.setAccessible(true);
    ReflectEntity entity = (ReflectEntity) constructor.newInstance("233", 1);
    System.out.println("构造entity成功");

    String typeName = clazz.getTypeName();
    System.out.println("type name: " + typeName);

    ClassLoader classLoader = clazz.getClassLoader();
    System.out.println("class loader: " + classLoader);
  }
}

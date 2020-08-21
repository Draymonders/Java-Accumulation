package cn.draymonder;

import cn.draymonder.entity.ReflectEntity;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class ReflectField {

  public static void main(String[] args)
      throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException {
    Class<?> clazz = Class.forName("cn.draymonder.entity.ReflectEntity");
    Constructor constructor = clazz.getDeclaredConstructor(String.class, int.class);
    constructor.setAccessible(true);
    ReflectEntity entity = (ReflectEntity) constructor.newInstance("233", 1);
    System.out.println("构造entity成功");

    System.out.println("****");
    for (Field field : clazz.getFields()) {
      System.out.println("get filed: " + field);
    }
    System.out.println("----");

    for (Field field : clazz.getDeclaredFields()) {
      System.out.println("get declared filed: " + field);
    }
    System.out.println("----");

    Field name = clazz.getDeclaredField("name");
    // 为true，表示 打开访问private的权限
    name.setAccessible(true);

    String originName = (String) name.get(entity);
    System.out.println("use reflect to get origin name: " + originName);
    System.out.println("get origin name: " + entity.getName());
    System.out.println("====");

    name.set(entity, "Draymonder");
    String currentName = (String) name.get(entity);
    System.out.println("use reflect to set name: " + currentName);
    System.out.println("get current name: " + entity.getName());
    System.out.println("++++");
  }
}

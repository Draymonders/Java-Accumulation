package org.simpleframework.util;

import java.io.File;
import java.io.FileFilter;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;

/**
 * @Description: 相关的类实现
 * @Date 2020/08/22 09:01
 * @auther Draymonder
 */
@Slf4j
public class ClassUtil {

  /**
   * 获取包名下对应的类的集合
   *
   * @param packageName 包名
   * @return 类集合
   */
  public static Set<Class<?>> extractPackageClass(String packageName) {
    ClassLoader classLoader = getClassLoader();
    URL url = classLoader.getResource(packageName.replace(".", "/"));
    if (url == null) {
      log.warn("current packageName: {} not exists", packageName);
      return null;
    }
    Set<Class<?>> classSet = new HashSet<>();
    if (url.getProtocol().equalsIgnoreCase("file")) {
      File packageDir = new File(url.getPath());
      extractClass(classSet, packageDir, packageName);
    }
    return classSet;
  }

  /**
   * 提取packageDir下所有的class, 并且放入到classSet中
   *
   * @param classSet           最终的class集合
   * @param packageDir         目录
   * @param packageNamePrefiex 包前缀
   */
  private static void extractClass(Set<Class<?>> classSet, File packageDir,
      String packageNamePrefiex) {
    if (packageDir.isFile()) {
      return;
    }

    File[] files = packageDir.listFiles(new FileFilter() {
      @Override
      public boolean accept(File file) {
        if (file.isDirectory()) {
          return true;
        } else if (file.isFile()) {
          if (file.getAbsolutePath().endsWith(".class")) {
            addClassToClassSet(classSet, file, packageNamePrefiex);
          }
        }
        return false;
      }
    });
    // 递归去访问子目录下的class文件
    if (files != null) {
      for (File file : files) {
        extractClass(classSet, file, packageNamePrefiex);
      }
    }
  }

  /**
   * 将classFile加载为Class类, 并放入classSet中
   *
   * @param classSet           类集合
   * @param classFile          class文件
   * @param packageNamePrefiex 包前缀, 用于将绝对路径替换为相对路径
   */
  private static void addClassToClassSet(Set<Class<?>> classSet, File classFile,
      String packageNamePrefiex) {
    String absolutePath = classFile.getAbsolutePath();
    // 将xxx/yyy/zzz换成xxx.yyy.zzz
    String absoluteClassPath = absolutePath.replace(File.separator, ".");
    // 将绝对路径换成项目的相对路径
    String projectClassPath = absoluteClassPath
        .substring(absoluteClassPath.indexOf(packageNamePrefiex));
    // 去除掉末尾的.class
    projectClassPath = projectClassPath.substring(0, projectClassPath.lastIndexOf(".class"));
    log.info("find project class path: {}", projectClassPath);
    // 反射去加载类, 并且加入到classSet中
    Class<?> clazz = loadClass(projectClassPath);
    classSet.add(clazz);
  }

  /**
   * 加载classPath对应的类
   *
   * @param classPath 类名(package.class)
   * @return
   */
  public static Class<?> loadClass(String classPath) {
    try {
      return Class.forName(classPath);
    } catch (ClassNotFoundException e) {
      log.error("class path: {}, can not load class, errmsg: {}", classPath, e.getMessage());
      throw new RuntimeException();
    }
  }

  /**
   * 根据对应的Class, 实例化对象
   *
   * @param clazz
   * @param accessible
   * @param <T>
   * @return
   */
  public static <T> T newInstance(Class<T> clazz, boolean accessible) {
    try {
      Constructor<T> constructor = clazz.getDeclaredConstructor();
      if (accessible) {
        constructor.setAccessible(true);
      }
      log.info("new class instance: {}", clazz.getName());
      return (T) constructor.newInstance();
    } catch (Exception e) {
      log.error("class: {} new instance error, errmsg: {}", clazz.getName(), e.getMessage());
      throw new RuntimeException();
    }
  }

  /**
   * 利用反射实现如下功能 currentObject.field = fieldValue
   *
   * @param field         对应的字段
   * @param currentObject 对象
   * @param fieldValue    字段需要的赋值内容
   * @param accessible    是否可以设置私有变量
   */
  public static void setFieldValue(Field field, Object currentObject, Object fieldValue,
      boolean accessible) {
    if (accessible) {
      field.setAccessible(true);
    }

    try {
      field.set(currentObject, fieldValue);
    } catch (IllegalAccessException e) {
      log.error("set class {} filed {} value {} error", currentObject, field, fieldValue);
      throw new RuntimeException(String
          .format("set class [%s] filed [%s] value [%s] error", currentObject, field, fieldValue));
    }
  }

  /**
   * 获取类加载器
   *
   * @return 当前项目的类加载
   */
  public static ClassLoader getClassLoader() {
    return Thread.currentThread().getContextClassLoader();
  }


}

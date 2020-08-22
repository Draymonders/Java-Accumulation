package org.simpleframework.util;

import java.io.File;
import java.io.FileFilter;
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
   * @param classSet 最终的class集合
   * @param packageDir 目录
   * @param packageNamePrefiex 包前缀
   */
  private static void extractClass(Set<Class<?>> classSet, File packageDir, String packageNamePrefiex) {
    if (packageDir.isFile())
      return ;

    File[] files = packageDir.listFiles(new FileFilter() {
      @Override
      public boolean accept(File file) {
        if (file.isDirectory())
          return true;
        else if (file.isFile()) {
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
   * @param classSet  类集合
   * @param classFile  class文件
   * @param packageNamePrefiex  包前缀, 用于将绝对路径替换为相对路径
   */
  private static void addClassToClassSet(Set<Class<?>> classSet, File classFile, String packageNamePrefiex) {
    String absolutePath = classFile.getAbsolutePath();
    // 将xxx/yyy/zzz换成xxx.yyy.zzz
    String absoluteClassPath = absolutePath.replace(File.separator, ".");
    // 将绝对路径换成项目的相对路径
    String projectClassPath = absoluteClassPath.substring(absoluteClassPath.indexOf(packageNamePrefiex));
    // 去除掉末尾的.class
    projectClassPath = projectClassPath.substring(0, projectClassPath.lastIndexOf(".class"));
    log.info("find project class path: {}", projectClassPath);
    // 反射去加载类, 并且加入到classSet中
    Class<?> clazz = null;
    try {
      clazz = Class.forName(projectClassPath);
    } catch (ClassNotFoundException e) {
      log.error("load class: {} error, errmsg: {}", projectClassPath, e.getMessage());
//      e.printStackTrace();
    }
    classSet.add(clazz);
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

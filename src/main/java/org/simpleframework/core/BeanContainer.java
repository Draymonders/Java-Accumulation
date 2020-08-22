package org.simpleframework.core;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.simpleframework.core.annotation.Componenet;
import org.simpleframework.core.annotation.Controller;
import org.simpleframework.core.annotation.Repository;
import org.simpleframework.core.annotation.Service;
import org.simpleframework.util.ClassUtil;
import org.simpleframework.util.ValidationUtil;

/**
 * @Description: Beans容器
 * @Date 2020/08/22 12:47
 * @auther Draymonder
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BeanContainer {

  /**
   * beansMap存储 class-> objectImpl 映射
   */
  private Map<Class<?>, Object> beansMap = new ConcurrentHashMap<>();
  private List<Class<? extends Annotation>> BEAN_ANNOTATIONS = Arrays.asList(Componenet.class,
      Repository.class, Service.class, Controller.class);
  /**
   * 是否加载过
   */
  private boolean isLoad = false;

  /**
   * 获取Bean容器实例
   *
   * @return 单例Bean容器
   */
  public static BeanContainer getInstance() {
    return ContainerHolder.HOLDER.instance;
  }

  public boolean isLoaded() {
    return isLoad;
  }

  public int beansSize() {
    return beansMap.size();
  }

  /**
   * 加载对应的basePackageName下的所有bean, 放入beansMap
   *
   * @param basePackageName
   */
  public synchronized void loadBeans(String basePackageName) {
    // 如果已经加载过, 就跳过
    if (isLoaded()) {
      return;
    }
    Set<Class<?>> classSet = ClassUtil.extractPackageClass(basePackageName);
    if (ValidationUtil.isEmpty(classSet)) {
      log.warn("base package: {} no class", basePackageName);
    } else {
      for (Class<?> clazz : classSet) {
        // 类上是否有四个注解之一
        boolean isBean = false;
        for (Class<? extends Annotation> annotationClass : BEAN_ANNOTATIONS) {
          if (clazz.isAnnotationPresent(annotationClass)) {
            isBean = true;
          }
        }
        if (isBean) {
          beansMap.put(clazz, ClassUtil.newInstance(clazz, true));
        }
      }
    }
    isLoad = true;
  }

  /**
   * 增加bean
   *
   * @param clazz bean的类对象
   * @param bean  bean实例
   * @return 之前的bean实例
   */
  public Object addBean(Class<?> clazz, Object bean) {
    return beansMap.put(clazz, bean);
  }

  /**
   * 删除bean
   *
   * @param clazz 要删除的bean的类对象
   * @return 删除的bean的实例, 如若没有 返回空值
   */
  public Object removeBean(Class<?> clazz) {
    return beansMap.remove(clazz);
  }

  /**
   * 获取所有的bean的类信息
   *
   * @return beans的类集合
   */
  public Set<Class<?>> getClassSet() {
    return beansMap.keySet();
  }

  /**
   * 获取所有的bean实例
   *
   * @return beans实例
   */
  public Set<Object> getObjectSet() {
    return new HashSet<>(beansMap.values());
  }

  /**
   * 根据class获取bean
   */
  public Object getBeanByClass(Class<?> clazz) {
    return beansMap.get(clazz);
  }

  /**
   * 根据注解获取对应的beans
   *
   * @param annotation 注解类
   * @return 获取到注解标注的累
   */
  public Set<Class<?>> getBeansByAnnotation(Class<? extends Annotation> annotation) {
    Set<Class<?>> classSet = getClassSet();
    if (ValidationUtil.isEmpty(classSet)) {
      log.warn("get class set empty");
      return Set.of();
    }
    Set<Class<?>> set = new HashSet<>();
    for (Class<?> clazz : classSet) {
      if (clazz.isAnnotationPresent(annotation)) {
        set.add(clazz);
      }
    }
    return set;
  }

  /**
   * 根据interface or superClass 获取对应的bean
   *
   * @param superClassOrInterface 类或者接口
   * @return 实现superClassOrInterface的类名
   */
  public Set<Class<?>> getBeansBySuperClassOrInterface(Class<?> superClassOrInterface) {
    Set<Class<?>> classeSet = getClassSet();
    Set<Class<?>> set = new HashSet<>();
    if (ValidationUtil.isEmpty(classeSet)) {
      log.warn("could not get class set");
    } else {
      for (Class<?> clazz : classeSet) {
        if (superClassOrInterface.isAssignableFrom(clazz) && !clazz.equals(superClassOrInterface)) {
          set.add(clazz);
        }
      }
    }
    return set;
  }

  private enum ContainerHolder {
    HOLDER;
    private BeanContainer instance;

    ContainerHolder() {
      instance = new BeanContainer();
    }
  }

}

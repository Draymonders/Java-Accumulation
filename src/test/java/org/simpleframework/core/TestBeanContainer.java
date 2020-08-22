package org.simpleframework.core;

import cn.draymonder.controller.MainPageController;
import cn.draymonder.service.solo.HeadLineService;
import cn.draymonder.service.solo.impl.HeadLineServiceImpl;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;
import org.simpleframework.core.annotation.Controller;
import org.simpleframework.core.annotation.Service;

/**
 * @Description: bean容器测试
 * @Date 2020/08/22 13:34
 * @auther Draymonder
 */
@TestMethodOrder(OrderAnnotation.class)
@TestInstance(Lifecycle.PER_CLASS)
@Slf4j
public class TestBeanContainer {

  private BeanContainer beanContainer;

  @BeforeAll
  public void beforeAll() {
    beanContainer = BeanContainer.getInstance();
  }

  @DisplayName("loadBeans: 容器加载bean")
  @Order(1)
  @Test
  public void testLoadBeans() {
    Assertions.assertEquals(beanContainer.isLoaded(), false);
    Assertions.assertEquals(beanContainer.beansSize(), 0);
    beanContainer.loadBeans("cn.draymonder");
    Assertions.assertEquals(beanContainer.isLoaded(), true);
    System.out.println("bean size:" + beanContainer.beansSize());
  }

  @Test
  @DisplayName("addBean: 增加Bean")
  @Order(2)
  public void testAddBean() {
    Object val1 = beanContainer.addBean(String.class, "2333");
    Assertions.assertNull(val1);
    Object val2 = beanContainer.addBean(String.class, "2444");
    Assertions.assertEquals(val2, "2333");
  }

  @Test
  @DisplayName("removeBean: 删除Bean")
  @Order(3)
  public void testRemoveBean() {
    Object val1 = beanContainer.removeBean(String.class);
    Assertions.assertEquals("2444", val1);
    Object val2 = beanContainer.removeBean(String.class);
    Assertions.assertNull(val2);
  }

  @Test
  @DisplayName("getClassSet and getObjectSet: 获取KeySet和valueSet")
  @Order(4)
  public void testGetClassSet() {
    Set<Class<?>> classSet = beanContainer.getClassSet();
    Set<Object> objectSet = beanContainer.getObjectSet();
    Assertions.assertEquals(classSet.size(), objectSet.size());
    log.info("class set size: {}", classSet.size());
  }

  @Test
  @DisplayName("getBeanByClass")
  @Order(5)
  public void testGetBeanByClass() {
    Object beanByClass = beanContainer.getBeanByClass(MainPageController.class);
    log.info("get bean by class: {}", beanByClass.getClass());
    Assertions.assertEquals(beanByClass.getClass(), MainPageController.class);
  }

  @Test
  @DisplayName("getBeansByAnnotation")
  @Order(6)
  public void testGetBeansByAnnotation() {
    Set<Class<?>> beansByService = beanContainer.getBeansByAnnotation(Service.class);
    log.info("get beans by service: {}", beansByService);
    Assertions.assertTrue(beansByService.size() > 0);
    Set<Class<?>> beansByController = beanContainer.getBeansByAnnotation(Controller.class);
    log.info("get beans by controller annotation: {}", beansByController);
    Assertions.assertTrue(beansByController.size() > 0);
  }

  @Test
  @DisplayName("getBeansBySuperClassOrInterface")
  @Order(7)
  public void testGetBeansBySuperClassOrInterface() {
    Set<Class<?>> beansBySuperClassOrInterface = beanContainer
        .getBeansBySuperClassOrInterface(HeadLineService.class);
    log.info("get by super {}", beansBySuperClassOrInterface);
    Assertions.assertTrue(beansBySuperClassOrInterface.contains(HeadLineServiceImpl.class));
  }
}

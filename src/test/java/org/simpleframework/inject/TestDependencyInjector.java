package org.simpleframework.inject;


import cn.draymonder.controller.MainPageController;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.simpleframework.core.BeanContainer;

/**
 * @Description: 依赖注入测试类
 * @Date 2020/08/22 19:35
 * @auther Draymonder
 */
@Slf4j
public class TestDependencyInjector {

  @Test
  public void testDoIoC() {
    String basePackageName = "cn.draymonder";
    DependencyInjector dependencyInjector = new DependencyInjector(basePackageName);
    BeanContainer beanContainer = BeanContainer.getInstance();

    // 加载basePackage下所有的bean
    dependencyInjector.loadBeans();

    MainPageController mainPageController = (MainPageController) beanContainer
        .getBeanByClass(MainPageController.class);
    Assertions.assertNotNull(mainPageController);
    Assertions.assertNull(mainPageController.getMainPageInfoService());

    // 依赖注入
    dependencyInjector.doIoC();
    Assertions.assertNotNull(mainPageController.getMainPageInfoService());
  }
}

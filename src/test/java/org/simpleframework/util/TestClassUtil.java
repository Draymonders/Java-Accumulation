package org.simpleframework.util;

import java.io.File;
import java.util.Set;
import org.junit.Test;

/**
 * @Description: ClassUtil测试
 * @Date 2020/08/22 10:29
 * @auther Draymonder
 */
public class TestClassUtil {

  /**
   * 测试提取class效果
   */
  @Test
  public void testExtractPackageClass() {
    Set<Class<?>> classes = ClassUtil.extractPackageClass("cn.draymonder.entity");
    System.out.println(classes);
  }

  @Test
  public void testFilesNullAndForEach() {
    File[] files = null;
    // 必须先要校验空指针
    for (File file : files) {
    }
  }
}

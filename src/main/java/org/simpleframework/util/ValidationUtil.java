package org.simpleframework.util;

import java.util.Collection;

/**
 * @Description: 校验工具
 * @Date 2020/08/22 13:21
 * @auther Draymonder
 */
public class ValidationUtil {

  public static boolean isEmpty(String str) {
    return str == null || "".equals(str);
  }

  public static boolean isEmpty(String[] objs) {
    return objs == null || objs.length == 0;
  }

  public static boolean isEmpty(Collection collections) {
    return collections == null || collections.isEmpty();
  }

  public static <T> boolean isEmpty(T[] objs) {
    return objs == null || objs.length == 0;
  }

  public static boolean isEmpty(Object val) {
    return val == null;
  }
}

package demo.api.guava;

import com.google.common.collect.ImmutableSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Description: 不可变集合
 * @Date 2020/10/15 22:28
 * @auther Draymonder
 */
public class ImmutableSample {

  public static List<Integer> list = new ArrayList<>();

  static {
    list.add(1);
    list.add(2);
    list.add(3);
  }

  public static void main(String[] args) {
    // testJDKImmutableCollection();
    testGuavaImmutableCollection();
  }

  /**
   * jdk原生 immutable collection
   */
  public static void testJDKImmutableCollection() {
    List<Integer> res = Collections.unmodifiableList(list);
    res.remove(0);
    System.out.println(res);
  }

  /**
   * Guava immutable collection
   */
  public static void testGuavaImmutableCollection() {
    // 创建方式有3种
    ImmutableSet<Integer> res = ImmutableSet.copyOf(list);
    System.out.println("res: " + res);

    ImmutableSet<Integer> res1 = ImmutableSet.of(1, 2, 3);
    System.out.println("res1: " + res1);

    ImmutableSet<Object> res2 = ImmutableSet.builder().add("haha").add("haha").add("haha").build();
    System.out.println("res2: " + res2);
  }
}

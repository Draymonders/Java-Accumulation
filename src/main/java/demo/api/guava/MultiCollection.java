package demo.api.guava;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: 多值容器
 * @Date 2020/10/15 22:49
 * @auther Draymonder
 */
public class MultiCollection {

  public static void main(String[] args) {
    testJDKMultiCollection();
    testGuavaMultiCollection();
  }

  /**
   * jdk多值容器
   */
  public static void testJDKMultiCollection() {
    Map<String, List<Integer>> map = new HashMap<>();
    map.put("1", List.of(1, 11, 111));
    map.put("2", List.of(2, 22, 222));
    System.out.println("jdk: " + map);
  }

  /**
   * guava多值容器
   */
  public static void testGuavaMultiCollection() {
    Multimap<String, Integer> multimap = HashMultimap.create();
    multimap.put("1", 1);
    multimap.put("1", 11);
    multimap.put("1", 111);

    multimap.put("2", 2);
    multimap.put("2", 22);
    multimap.put("2", 22);
    Map<String, Collection<Integer>> res
        = multimap.asMap();
    System.out.println("guava: " + res);
    System.out.println("size: " + multimap.size());
  }

}

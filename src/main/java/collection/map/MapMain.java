package collection.map;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MapMain {

  public static void main(String[] args) {
    // HashMapMain.testKeyIsNull();
    // HashMapMain.testMultiValueIsNull();
  }

  static class ConcurrentHashMapMain {
    // TODO, 阅读concurrentHashMap源码

    static void testPut() {
      ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>(2);
      map.put("233", "244");
      System.out.println(map.get("233"));
    }
  }


  static class HashMapMain {

    /**
     * 测试key能不能为null
     */
    static void testKeyIsNull() {
      HashMap<String, String> map = new HashMap<>(2);
      map.put(null, "111");
      map.put(null, "233");
      System.out.println(map.get(null));
    }

    /**
     * 测试可不可以value为null
     */
    static void testMultiValueIsNull() {
      HashMap<String, String> map = new HashMap<>(2);
      map.put("111", null);
      map.put("233", null);
      System.out.println(map.get("111"));
      System.out.println(map.get("233"));
    }

  }

}

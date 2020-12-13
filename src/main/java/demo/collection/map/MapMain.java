package demo.collection.map;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapMain {

  static void testMapComputeIfAbsent1() {
    HashMap<String, String> map = new HashMap<>();

    map.put("aaa", "aaaa");
    map.put("aaa", "a");
    // System.out.println("map:" + map);
    /*
    String res1 = map.get("ccc");
    String res2 = map.get(null);

    System.out.println(res1);
    System.out.println(res2);
    */

    String aaa = map.computeIfAbsent("aaa", (val) -> {
      System.out.println("aaaaa---");
      return val;
    });

    String bbb = map.computeIfAbsent("bbb", (val) -> {
      System.out.println("bbbbb----");
      return "haha";
    });
    System.out.println(aaa);
    System.out.println(bbb);
  }


  private static void testMapComputeIfAbsent2() {
    Map<String, List<String>> map = new HashMap<>();
    String key = "aa";
    List<String> res = map.computeIfAbsent(key, (k) -> {
      System.out.println(String.format("not found key: %s", key));
      return List.of(key);
    });
    System.out.println(res);
  }

  public static void main(String[] args) {
    // testMapComputeIfAbsent1();
    testMapComputeIfAbsent2();
  }

}

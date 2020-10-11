package demo.collection.map;

import java.util.HashMap;

public class MapMain {

  public static void main(String[] args) {
    HashMap<String, String> map = new HashMap<>();

    map.put("aaa", "aaaa");
    map.put("aaa", "a");
    System.out.println("map:" + map);
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
}

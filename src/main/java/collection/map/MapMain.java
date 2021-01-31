package collection.map;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicReference;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * map方式实验
 *
 * @author draymonder
 */
public class MapMain {

  public static void main(String[] args) {
    // HashMapMain.testKeyIsNull();
    // HashMapMain.testMultiValueIsNull();
    // HashMapMain.testThreadNotSafe();

    // ConcurrentHashMapMain.testRemove();
    // ConcurrentHashMapMain.testThreadSafe();

    // TreeMapMain.testUse();
    // TreeMapMain.testGetSequence();

    LinkedHashMapMain.testUse();
  }

  @NoArgsConstructor
  @AllArgsConstructor
  @EqualsAndHashCode
  @ToString
  static class Node implements Comparable<Node> {

    @Getter
    private int key;
    @Getter
    private String val;

    @Override
    public int compareTo(Node o) {
      if (o != null) {
        if (key == o.key) {
          return val.compareTo(o.val);
        }
        return (key < o.key) ? -1 : 1;
      }
      return -1;
    }
  }

  static class LinkedHashMapMain {

    /**
     * 简单使用
     */
    static void testUse() {
      LinkedHashMap<String, String> map = new LinkedHashMap<>(8, 0.75f, true);
      map.put("ddd", "desk");
      map.put("aaa", "ask");
      map.put("ccc", "check");
      map.keySet().forEach(System.out::println);
      System.out.println("====");
      map.put("aaa", "check1");
      map.keySet().forEach(System.out::println);
    }

  }


  static class TreeMapMain {

    /**
     * 简单使用
     */
    static void testUse() {
      // node -> score
      TreeMap<Node, Integer> scoreMap = new TreeMap<>();
      scoreMap.put(new Node(1, "233"), 2);
      scoreMap.put(new Node(2, "2444"), 3);
      scoreMap.put(new Node(1, "111"), 1);
      for (Entry<Node, Integer> entry : scoreMap.entrySet()) {
        System.out.println(entry.getKey() + " -> " + entry.getValue());
      }
      System.out.println("=== first node ===");
      Entry<Node, Integer> entry = scoreMap.pollFirstEntry();
      System.out.println(entry.getKey() + " -> " + entry.getValue());
      System.out.println("map size: " + scoreMap.size());
    }

    static void testGetSequence() {
      TreeMap<Node, Integer> scoreMap = new TreeMap<>();
      scoreMap.put(new Node(1, "233"), 2);
      scoreMap.put(new Node(2, "2444"), 3);
      scoreMap.put(new Node(1, "111"), 1);

      System.out.println("=== ceilng node ===");
      Entry<Node, Integer> entry = scoreMap.ceilingEntry(new Node(1, "444"));
      System.out.println(entry.getKey() + " -> " + entry.getValue());
      System.out.println("=====");

      System.out.println("=== floor node ===");
      entry = scoreMap.floorEntry(new Node(1, "123"));
      System.out.println(entry.getKey() + " -> " + entry.getValue());
      System.out.println("=====");
    }

  }

  static class ConcurrentHashMapMain {

    /**
     * Import 测试线程安全
     */
    static void testThreadSafe() {
      HashMap<String, String> map = new HashMap<>(4);
      ExecutorService removeThreadPool = Executors.newFixedThreadPool(2);
      ExecutorService getThreadPool = Executors.newFixedThreadPool(2);
      int n = 10;
      int m = 10;
      for (int i = 0; i < n; i++) {
        map.put(String.valueOf(i), String.valueOf(i));
      }

      List<Future<String>> futureList = new ArrayList<>();
      for (int i = 0; i < m; i++) {
        Future<String> resultFuture = getThreadPool.submit(() -> {
          AtomicReference<String> val = new AtomicReference<>("");
          map.forEach((k, v) -> {
            val.set(v + "233");
          });
          return val.get();
        });
        futureList.add(resultFuture);
      }

      for (int i = 0; i < n; i++) {
        final int x = i;
        removeThreadPool.execute(() -> {
          // 以下两句任选其一都会线程不安全
          map.remove(String.valueOf(x));
          // map.put(String.valueOf(2*x), "2333");
        });
      }

      for (Future<String> future : futureList) {
        try {
          System.out.println(future.get());
        } catch (Exception e) {
          e.printStackTrace();
        }
      }

      try {
        Thread.sleep(5 * 1000);
      } catch (InterruptedException e) {
        System.out.println();
      }
      getThreadPool.shutdown();
      removeThreadPool.shutdown();
    }

    /**
     * key 或者 value 都不能为null
     */
    static void testKeyOrValueCanNotNull() {
      // key 或者 value都不能为null
      ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>(2);
      map.put(null, null);
      map.get(null);
    }

    /**
     * 查看remove方法实现
     */
    static void testRemove() {
      ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>(2);
      map.put("233", "233");
      map.put("244", "244");
      map.remove("233");
    }

  }


  static class HashMapMain {

    /**
     * Import 测试线程不安全, 报错ConcurrentModificationException
     */
    static void testThreadNotSafe() {
      HashMap<String, String> map = new HashMap<>(4);
      ExecutorService removeThreadPool = Executors.newFixedThreadPool(2);
      ExecutorService getThreadPool = Executors.newFixedThreadPool(2);
      int n = 10;
      int m = 10;
      for (int i = 0; i < n; i++) {
        map.put(String.valueOf(i), String.valueOf(i));
      }

      List<Future<String>> futureList = new ArrayList<>();
      for (int i = 0; i < m; i++) {
        Future<String> resultFuture = getThreadPool.submit(() -> {
          AtomicReference<String> val = new AtomicReference<>("");
          map.forEach((k, v) -> {
            val.set(v + "233");
          });
          return val.get();
        });
        futureList.add(resultFuture);
      }

      for (int i = 0; i < n; i++) {
        final int x = i;
        removeThreadPool.execute(() -> {
          // 以下两句任选其一都会线程不安全
          map.remove(String.valueOf(x));
          // map.put(String.valueOf(2*x), "2333");
        });
      }

      for (Future<String> future : futureList) {
        try {
          System.out.println(future.get());
        } catch (Exception e) {
          e.printStackTrace();
        }
      }

      try {
        Thread.sleep(5 * 1000);
      } catch (InterruptedException e) {
        System.out.println();
      }
      getThreadPool.shutdown();
      removeThreadPool.shutdown();
    }


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

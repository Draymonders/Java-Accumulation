package collection.list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Function;
import java.util.stream.Collectors;


public class ListMain {

  public static void main(String[] args) {
    // testRemove();
    // testSingleLineWriteData();
    // testCopyOfArray();
    // testCountWord();
    // testCopyOnWriteList();
  }

  /**
   * 测试copyOnWrite
   */
  private static void testCopyOnWriteList() {
    CopyOnWriteArrayList<String> arr = new CopyOnWriteArrayList<>(
        List.of("244", "255", "266", "277"));
    Iterator<String> iterator = arr.iterator();
    while (iterator.hasNext()) {
      String next = iterator.next();
      System.out.print(next.hashCode() + " ");
    }
    System.out.println();
    System.out.println("=== add item ===");
    arr.set(1, "3");

    iterator = arr.iterator();
    while (iterator.hasNext()) {
      String next = iterator.next();
      System.out.print(next.hashCode() + " ");
    }
    System.out.println();
  }


  /**
   * 给定 list，表示 id，可能重复。要求用 stream 实现 list -> map 的转换，map 统计 id 出现的次数
   */
  private static void testCountWord() {
    List<String> list = List.of("1", "2", "3", "1", "6", "4", "6");

    Map<String, Long> map = list.stream()
        .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

    System.out.println(map);
  }

  /**
   * 测试数组的copy
   */
  private static void testCopyOfArray() {
    ArrayList<String> arr = new ArrayList<>() {
      {
        add("1");
        add("2");
        add("3");
      }
    };
    Object[] objects = arr.toArray();
    Arrays.stream(objects).forEach(System.out::println);

    Object[] copyOfObjects = Arrays.copyOf(objects, objects.length);
    Arrays.stream(copyOfObjects).forEach(System.out::println);

    System.out.println("objects: " + objects.toString());
    System.out.println("copyOfObjects: " + copyOfObjects.toString());
  }

  /**
   * 测试一行代码加入集合数据
   */
  private static void testSingleLineWriteData() {
    ArrayList<Integer> arr = new ArrayList<>() {
      {
        add(1);
        add(2);
        add(3);
      }
    };
    System.out.println(arr.size());
  }

  /**
   * 测试ArrayList的remove方法
   */
  private static void testRemove() {
    List<String> arr1 = List.of("11", "22", "33");
    ArrayList<String> arr = new ArrayList<>(arr1);

    arr.addAll(arr1);

    boolean contains = arr.contains("11");
    System.out.println("contains \"11\":" + contains);

    arr.remove(1);
    arr.remove("33");
    System.out.println("list remove index [1] then: " + arr);
    System.out.println("====");
  }

}

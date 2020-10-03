package demo.collection.list;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Date 2020/09/03 23:31
 * @auther Draymonder
 */
public class ArrayListMain {

  public static void main(String[] args) {
    testRemove();
  }

  /**
   * 测试ArrayList的remove方法
   */
  public static void testRemove() {
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

package demo.collection.list;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @Description:
 * @Date 2020/09/20 18:57
 * @auther Draymonder
 */
public class CopyOnWriteListMain {

  public static void main(String[] args) {
    CopyOnWriteArrayList<Integer> arr = new CopyOnWriteArrayList<>(List.of(1, 2, 3, 4));
    System.out.println("copy on write");
  }
}

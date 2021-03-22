package collection.failfast;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * fail fast尝试
 *
 * @author Draymonder
 */
public class FailFastMain {

  public static void main(String[] args) {
//    testNotFastFail();
//    testFastFail();
    testNeedWrite();
  }

  /**
   * 真正的写法
   */
  public static void testNeedWrite() {
    List<String> list = new ArrayList<>();
    list.add("111");
    list.add("222");
    list.add("333");
    Iterator<String> it = list.iterator();
    while (it.hasNext()) {
      if (it.next().equals("222")) {
        it.remove();
        break;
      }
    }
    System.out.println(list);
  }

  /**
   * 没有fast fail
   */
  private static void testNotFastFail() {
    List<String> list = new ArrayList<>();
    list.add("111");
    list.add("222");
    list.add("333");

    for (String str : list) {
      if ("222".equals(str)) {
        list.remove(str);
      }
    }

    System.out.println(list);
  }

  /**
   * fast fail掉了
   */
  private static void testFastFail() {
    List<String> list = new ArrayList<>();
    list.add("沉默王二");
    list.add("沉默王三");
    list.add("一个文章真特么有趣的程序员");

    for (String str : list) {
      if ("沉默王二".equals(str)) {
        list.remove(str);
      }
    }

    System.out.println(list);
  }
}

package demo.api.stream;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.OptionalInt;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @Description: 学习stream
 * @Date 2020/10/11 20:43
 * @auther Draymonder
 */
public class StreamDemo {

  static final List<String> list = List.of("a", "b", "ccc", "d", "c", "e");
  static final List<Integer> intList = List.of(1, 2, 3, 4, 5);

  public static void main(String[] args) {
    // streamProcess();
    // flatMapProcess();
    // peekProcess();
    // distinctProcess();
    // skipProcess();
    // limitProcess();
    // maxProcess();
  }

  /**
   * max使用
   */
  static void maxProcess() {
    OptionalInt max = intList.stream().mapToInt(i -> i).max();
    max.ifPresent(System.out::println);
  }

  /**
   * limit使用：截断前N条记录。有状态操作
   */
  private static void limitProcess() {
    List<String> res = list.stream().sorted(String::compareTo).skip(1).limit(3)
        .collect(Collectors.toList());
    System.out.println(res);
  }


  /**
   * skip使用：跳过前N条记录。有状态操作
   */
  static void skipProcess() {
    List<String> res = list.stream().sorted(String::compareTo).skip(3)
        .collect(Collectors.toList());
    System.out.println(res);
  }


  /**
   * distinct使用：对流元素进行去重。有状态操作
   */
  static void distinctProcess() {
    List<String> res = list.stream().flatMap(str -> Arrays.stream(str.split(""))).distinct().collect(Collectors.toList());
    System.out.println(res);
  }

  /**
   * peek使用：对流中元素进行遍历操作，与forEach类似，但不会销毁流元素
   */
  static void peekProcess() {
    List<String> res = list.stream().peek(System.out::println)
        .collect(Collectors.toList());
    System.out.println(res);
  }


  /**
   * flatMap扁平化处理
   */
  static void flatMapProcess() {
    List<String> res = list.stream().flatMap((str) -> Arrays.stream(str.split("")))
        .collect(Collectors.toList());
    System.out.println(res);
  }


  /**
   * stream流真实处理 (是一个元素一个元素去处理的)
   */
  static void streamProcess() {
    list.stream()
        .peek(str -> System.out.println("before: " + str))
        .filter(str -> !"c".equals(str))
        .peek(str -> System.out.println("after :" + str))
        .sorted((str1, str2) -> {
          if (str1.compareTo(str2) > 0) {
            return -1;
          } else if (str1.compareTo(str2) == 0) {
            return 0;
          }
          return 1;
        })
        .collect(Collectors.toList());

    System.out.println("===");
    System.out.println(list);
  }
}

package demo.api.stream;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Description: 常见预定义收集器使用
 * @Date 2020/10/11 22:10
 * @auther Draymonder
 */
public class StreamCollector {

  public static void main(String[] args) {
    // groupProcess();
    // partitionProcess();
  }

  /**
   * 分区
   */
  private static void partitionProcess() {
    Stream<Integer> stream = Stream.of(1, 2, 3, 4, 5);
    // key为true/false, value为对应条件下的集合
    Map<Boolean, List<Integer>> res
        = stream.collect(Collectors.partitioningBy(i -> (i >= 3)));
    System.out.println(res);
  }

  /**
   * 分组
   */
  private static void groupProcess() {
    // key为分组条件, value为分组后的集合
    Stream<String> stream = Stream.of("a", "b", "c", "a");
    Map<String, List<String>> res = stream.collect(Collectors.groupingBy(str -> str));
    System.out.println(res);
  }
}

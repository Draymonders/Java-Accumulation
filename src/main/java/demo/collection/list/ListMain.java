package demo.collection.list;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @Description:
 * @Date 2020/09/03 23:31
 * @auther Draymonder
 */
public class ListMain {

  /**
   * 给定 list，表示 id，可能重复。要求用 stream 实现 list -> map 的转换，map 统计 id 出现的次数
   */
  public static void countWord() {
    List<String> list = List.of("1", "2", "3", "1", "6", "4", "6");

    Map<String, Long> map = list.stream()
        .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    
    System.out.println(map);
  }

  public static void main(String[] args) {
    countWord();
  }
}

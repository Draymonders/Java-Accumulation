package demo.api.optional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * @Description:
 * @Date 2020/10/14 22:09
 * @auther Draymonder
 */
public class OptionalSample {

  public static void main(String[] args) {
//    optionalConstructor();
    optionalStream(null);
    optionalStream(List.of("1", "2", "3", "4"));
  }


  /**
   * 构建optional
   */
  public static void optionalConstructor() {
    /**
     * 声明为空
     */
    Optional.empty();
    /**
     * 不能为null
     */

    Optional.of(new Object());
    /**
     * 可以为空
     */
    Optional.ofNullable(null);
  }

  /**
   * 避免空指针
   */
  public static void optionalStream(List<String> list) {
    Optional.ofNullable(list)
        .orElse(new ArrayList<>())
        .forEach(System.out::println);

    Optional.ofNullable(list)
        .map(List::stream)
        .orElseGet(Stream::empty)
        .forEach(System.out::println);
  }

}

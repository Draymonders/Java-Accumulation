package demo.api.stream;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @Description: 流的构建形式
 * @Date 2020/10/11 21:23
 * @auther Draymonder
 */
public class StreamConstructor {

  public static void main(String[] args) throws IOException {
    // streamFromValue();
    // streamFromArray();
    // streamFromFile();
    // streamFromFunction();
  }

  /**
   * 通过函数生成流（无限流）
   */
  private static void streamFromFunction() {
    Stream<Double> stream = Stream.generate(Math::random);
    stream.limit(10).forEach(System.out::println);

    Stream.iterate(0, i->i+1).limit(5).forEach(System.out::println);
  }

  /**
   * 由数值直接构建流
   */
  static void streamFromValue() {
    Stream<Integer> stream = Stream.of(1, 2, 3, 4, 5);
    stream.forEach(System.out::println);
  }

  /**
   * 通过数组构建流
   */
  static void streamFromArray() {
    int[] arr = new int[]{1, 2, 3, 4};
    IntStream stream = Arrays.stream(arr);
    stream.forEach(System.out::println);
  }

  /**
   * 通过文件生成流
   *
   * @throws IOException
   */
  static void streamFromFile() throws IOException {
    String filePath = "/home/draymonder/Desktop/practice/Java-Accumulation/src/main/java/demo/api/stream/StreamConstructor.java";
    Stream<String> lines = Files.lines(Path.of(filePath));
    lines.forEach(System.out::println);
  }

}

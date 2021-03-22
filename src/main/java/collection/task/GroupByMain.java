package collection.task;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.summingInt;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * student (name, course, score)
 * <p>
 * 给一个student list 返回一个list (name, sum(score)
 */
public class GroupByMain {

  public static void main(String[] args) {
    List<Student> students = IntStream.range(0, 10)
        .mapToObj(i -> new Student(String.valueOf(i % 2), i)).collect(Collectors.toList());

    Map<String, Integer> name2score = students.stream()
        .collect(groupingBy(Student::getName, summingInt(Student::getScore)));
    List<Student> res = name2score.entrySet().stream()
        .map(entry -> new Student(entry.getKey(), entry.getValue()))
        .collect(Collectors.toList());

    res.forEach(item -> {
      System.out.println(item.getName() + " == " + item.getScore());
    });

  }

  @AllArgsConstructor
  @Getter
  static class Student {

    String name;
    int score;
  }
}

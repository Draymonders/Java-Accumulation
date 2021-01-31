package collection.set;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 学习使用set
 *
 * @author Draymonder
 * @date 2021/01/30 20:36
 */
public class SetMain {

  public static void main(String[] args) {
    // HashSetMain.testUse();
    // HashSetMain.testGetSetIntersect();

    TreeSetMain.testUse();
  }

  @NoArgsConstructor
  @AllArgsConstructor
  @EqualsAndHashCode
  @ToString
  static class Node implements Comparable<Node> {

    @Getter
    private int key;
    @Getter
    private String val;

    @Override
    public int compareTo(Node o) {
      if (o != null) {
        if (key == o.key) {
          return val.compareTo(o.val);
        }
        return (key < o.key) ? -1 : 1;
      }
      return -1;
    }
  }

  static class TreeSetMain {

    /**
     * 使用treeSet
     */
    static void testUse() {
      TreeSet<Node> set = new TreeSet<>();
      set.add(new Node(1, "233"));
      set.add(new Node(2, "2444"));
      set.add(new Node(1, "111"));
      set.add(new Node(1, "111"));
      System.out.println(set.size());
    }
  }

  static class HashSetMain {

    /**
     * 使用hashSet
     */
    static void testUse() {
      HashSet<String> set = new HashSet<>();
      set.add("2333");
      set.add("2333");
      System.out.println(set.size());
    }

    /**
     * 求两个set的交集
     */
    static void testGetSetIntersect() {
      Set<String> set1 = Set.of("1", "2", "3", "4");
      Set<String> set2 = Set.of("2", "4", "5", "6");
      Set<String> intersect = set2.stream().filter(set1::contains)
          .collect(Collectors.toSet());
      System.out.println(intersect);
    }
  }

}

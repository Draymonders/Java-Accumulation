package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Solution1002 {

  public List<String> commonChars(String[] A) {
    List<Map<String, Integer>> wordMapList = Arrays.stream(A).map(str -> {
      Map<String, Integer> map = new HashMap<>();
      Arrays.stream(str.split("")).forEach(ch -> {
        int val = map.getOrDefault(ch, 0);
        map.put(ch, val + 1);
      });
      return map;
    }).collect(Collectors.toList());

//    wordMapList.stream().forEach(System.out::println);

    Map<String, Integer> res = new HashMap<>();
    boolean first = true;
    for (Map<String, Integer> map : wordMapList) {
      if (first) {
        res = map;
        first = false;
        continue;
      }

      Map<String, Integer> temp = new HashMap<>();
      Map<String, Integer> finalRes = res;
      res.keySet().forEach(ch -> {
        if (map.containsKey(ch)) {
          temp.put(ch, Math.min(finalRes.get(ch), map.get(ch)));
        }
      });
      res = temp;
//      System.out.println("res: " + res);
      first = false;
    }

//    System.out.println("===");
//    System.out.println(res);
//    System.out.println("####");
    List<String> ans = new ArrayList<>();
    for (String ch : res.keySet()) {
      int val = res.get(ch);
      IntStream.range(0, val).forEach(i -> ans.add(ch));
    }
    return ans;
  }

  public static void main(String[] args) {
    String[] A = new String[]{"bella", "label", "roller"};
    List<String> res = new Solution1002().commonChars(A);
    res.stream().forEach(System.out::println);
  }
}

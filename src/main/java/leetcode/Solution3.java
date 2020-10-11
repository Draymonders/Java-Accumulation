package leetcode;

import java.util.HashMap;

/**
 * @Description: 无重复字符的最长子串
 * @Date 2020/10/11 12:22
 * @auther Draymonder
 */
public class Solution3 {
  public int lengthOfLongestSubstring(String s) {
    HashMap<Integer, Integer> map = new HashMap<>();
    int st = 0;
    int res = 0;
    for (int i=0; i<s.length(); i++) {
      if (map.containsKey((int)s.charAt(i))) {
        st = Math.max(st, map.get((int)s.charAt(i)) + 1);
      }
      res = Math.max(res, i - st + 1);
      map.put((int) s.charAt(i), i);
    }
    return res;
  }

  public static void main(String[] args) {
    String s = "abba";
    int res = new Solution3().lengthOfLongestSubstring(s);
    System.out.println("res: " + res);
  }
}

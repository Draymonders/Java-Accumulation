package leetcode;

import java.util.Arrays;
import java.util.HashMap;

/**
 * @Description: 两数之和
 * @Date 2020/10/11 10:08
 * @auther Draymonder
 */
public class Solution1 {

  public int[] twoSum(int[] nums, int target) {
    HashMap<Integer, Integer> map = new HashMap<>();

    for (int i = 0; i < nums.length; i++) {
      if (map.containsKey(target - nums[i])) {
        return new int[]{map.get(target - nums[i]), i};
      }
      map.put(nums[i], i);
    }
    return new int[0];
  }

  public static void main(String[] args) {
    int[] nums = {2, 7, 11, 15};
    int[] res = new Solution1().twoSum(nums, 9);
    Arrays.stream(res).forEach(i -> System.out.print(nums[i] + " "));
  }
}

package leetcode;

import java.util.Arrays;

/**
 * @Description: 分割等和子集
 * @Date 2020/10/11 09:48
 * @auther Draymonder
 */
public class Solution416 {

  public boolean canPartition(int[] nums) {
    int n = nums.length;
    if (n == 0) {
      return true;
    }

    int sum = Arrays.stream(nums).reduce(0, Integer::sum);
    if (sum % 2 != 0) {
      return false;
    }
    int mid = sum / 2;

    boolean[] dp = new boolean[mid + 5];
    dp[0] = true;
    // IntStream.range(0, dp.length).forEach(i -> System.out.println(dp[i]));

    for (int i = 0; i < n; i++) {
      for (int j = mid; j >= nums[i]; j--) {
        dp[j] = (dp[j] || dp[j - nums[i]]);
      }
    }

    return dp[mid];
  }

  public static void main(String[] args) {
    int[] nums = {1, 5, 11, 5};
    boolean res = new Solution416().canPartition(nums);
    System.out.println("res: " + res);
  }
}

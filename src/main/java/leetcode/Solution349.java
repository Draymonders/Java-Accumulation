package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solution349 {

  /**
   * 方法一: 使用集合去做去重
   *
   * @return
   */
  public int[] impl1(int[] nums1, int[] nums2) {
    HashSet<Integer> set = new HashSet<>();
    for (int i = 0; i < nums1.length; i++) {
      set.add(nums1[i]);
    }
    Set<Integer> resultSet = new HashSet<>();
    for (int i = 0; i < nums2.length; i++) {
      if (set.contains(nums2[i])) {
        resultSet.add(nums2[i]);
      }
    }
    long[] list = resultSet.stream().mapToInt(Integer::intValue).asLongStream().toArray();
    int[] res = new int[list.length];
    for (int i = 0; i < res.length; i++) {
      res[i] = (int) list[i];
    }
    return res;
  }

  /**
   * 方法二: 排序后，使用双指针
   */
  public int[] impl2(int[] nums1, int[] nums2) {
    Arrays.sort(nums1);
    Arrays.sort(nums2);
    int i = 0, j = 0;
    List<Integer> list = new ArrayList<>();
    while (i < nums1.length && j < nums2.length) {
      while (i < nums1.length && nums1[i] < nums2[j]) {
        i++;
      }
      while (j < nums2.length && i < nums1.length && nums2[j] < nums1[i]) {
        j++;
      }
      if (i < nums1.length && j < nums2.length && nums1[i] == nums2[j]) {
        list.add(nums1[i]);
        i++;
        j++;
        while (i < nums1.length && nums1[i] == nums1[i - 1]) {
          i++;
        }
        while (j < nums2.length && nums2[j] == nums2[j - 1]) {
          j++;
        }
      }
    }
    int[] res = new int[list.size()];
    for (int k = 0; k < res.length; k++) {
      res[k] = list.get(k);
    }
    return res;
  }

  /**
   * 方法三: 排序，双指针，题解写法
   */
  public int[] impl3(int[] nums1, int[] nums2) {
    int i = 0, j = 0;
    int[] res = new int[nums1.length + nums2.length];
    Arrays.sort(nums1);
    Arrays.sort(nums2);
    int idx = 0;
    while (i < nums1.length && j < nums2.length) {
      if (nums1[i] == nums2[j]) {
        if ((idx > 0 && res[idx - 1] != nums1[i]) || idx == 0) {
          res[idx++] = nums1[i];
        }
        i++;
        j++;
      } else if (nums1[i] < nums2[j]) {
        i++;
      } else {
        j++;
      }
    }
    return Arrays.copyOfRange(res, 0, idx);
  }

  public int[] intersection(int[] nums1, int[] nums2) {
    return impl3(nums1, nums2);
  }

  public static void scan(int[] arr) {
    for (int i = 0; i < arr.length; i++) {
      System.out.print(arr[i] + " ");
    }
    System.out.println("");
    System.out.println("====");
  }

  public static void main(String[] args) {
    int[] n1 = new int[]{1, 2, 2, 1};
    int[] n2 = new int[]{2, 2};
    int[] intersection = new Solution349().intersection(n1, n2);
    scan(intersection);

    int[] n3 = new int[]{4, 9, 5};
    int[] n4 = new int[]{9, 4, 9, 8, 4};
    int[] intersection1 = new Solution349().intersection(n3, n4);
    scan(intersection1);

    int[] n5 = new int[]{9, 3, 7};
    int[] n6 = new int[]{6, 4, 1, 0, 0, 4, 4, 8, 7};
    int[] intersection2 = new Solution349().intersection(n5, n6);
    scan(intersection2);

  }
}

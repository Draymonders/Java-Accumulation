package leetcode;

import java.util.ArrayDeque;
import java.util.Queue;

public class Solution129 {

  /**
   * method one: depth first search
   */
  public int dfs(TreeNode rt, int sum) {
    if (rt == null) {
      return 0;
    }
    sum = sum * 10 + rt.val;
    if (rt.left == null && rt.right == null) {
      return sum;
    }
    return dfs(rt.left, sum) + dfs(rt.right, sum);
  }

  public int bfs(TreeNode rt) {
    if (rt == null) {
      return 0;
    }
    int res = 0;
    Queue<TreeNode> queue = new ArrayDeque<>();

    queue.add(rt);
    while (!queue.isEmpty()) {
      TreeNode temp = queue.poll();
      if (temp.left == null && temp.right == null) {
        res += temp.val;
      }
      if (temp.left != null) {
        temp.left.val += (temp.val) * 10;
        queue.offer(temp.left);
      }
      if (temp.right != null) {
        temp.right.val += (temp.val) * 10;
        queue.offer(temp.right);
      }
    }
    return res;
  }

  public int sumNumbers(TreeNode root) {
    return bfs(root);
  }

  public static void main(String[] args) {

  }
}

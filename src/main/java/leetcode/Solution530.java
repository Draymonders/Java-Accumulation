package leetcode;


class TreeNode {

  int val;
  TreeNode left;
  TreeNode right;

  TreeNode(int x) {
    val = x;
  }
}

/**
 * @Description: 二叉搜索树的最小绝对差
 * @Date 2020/10/12 22:06
 * @auther Draymonder
 */
public class Solution530 {

  int pre = -1;
  int res = 0;

  void inOrder(TreeNode root) {
    if (root != null) {
      inOrder(root.left);
      if (pre != -1) {
        res = Math.min(root.val - pre, res);
      }
      pre = root.val;
      inOrder(root.right);
    }
  }

  public int getMinimumDifference(TreeNode root) {
    pre = -1;
    res = Integer.MAX_VALUE;
    inOrder(root);
    return res;
  }
}

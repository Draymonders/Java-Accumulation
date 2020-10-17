package leetcode;

class Node {

  public int val;
  public Node left;
  public Node right;
  public Node next;

  public Node() {
  }

  public Node(int _val) {
    val = _val;
  }

  public Node(int _val, Node _left, Node _right, Node _next) {
    val = _val;
    left = _left;
    right = _right;
    next = _next;
  }
};

public class Solution116 {

  public Node connect(Node root) {
    if (root == null) {
      return root;
    }
    if (root.left != null) {
      root.left.next = root.right;
    }
    if (root.right != null && root.next != null) {
      root.right.next = root.next.left;
    }
    connect(root.right);
    connect(root.left);
    return root;
  }

  public static void main(String[] args) {
    Node rt = new Node(1);
    rt.left = new Node(2);
    rt.right = new Node(3);
    rt.left.left = new Node(4);
    rt.left.right = new Node(5);
    rt.right.left = new Node(6);
    rt.right.right = new Node(7);
    Node res = new Solution116().connect(rt);
    System.out.println(res.val);
    Node l1 = res.left;
    Node l2 = l1.left;
    while (l1 != null) {
      System.out.print(l1.val + " ");
      l1 = l1.next;
    }
    System.out.println();
    while (l2 != null) {
      System.out.print(l2.val + " ");
      l2 = l2.next;
    }
    System.out.println();
  }
}

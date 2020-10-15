package leetcode;


public class Solution24 {


  public ListNode dfs(ListNode head) {
    if (head == null || head.next == null) {
      return head;
    }
    ListNode temp = new ListNode();
    ListNode cur = head;
    ListNode next = cur.next;
    ListNode nextNext = next.next;
    temp.next = next;
    next.next = cur;
    cur.next = dfs(nextNext);
    return temp.next;
  }


  public ListNode swapPairs(ListNode head) {
    return dfs(head);
  }
}

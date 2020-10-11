package leetcode;

/**
 * @Description: 两数相加
 * @Date 2020/10/11 12:14
 * @auther Draymonder
 */

class ListNode {

  int val;
  ListNode next;

  ListNode() {
  }

  ListNode(int val) {
    this.val = val;
  }

  ListNode(int val, ListNode next) {
    this.val = val;
    this.next = next;
  }
}

public class Solution2 {

  public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
    ListNode dummyHead = new ListNode();
    ListNode cur = dummyHead;

    int up = 0;
    while (l1 != null || l2 != null || up > 0) {
      int v1 = 0, v2 = 0;
      if (l1 != null) {
        v1 = l1.val;
        l1 = l1.next;
      }

      if (l2 != null) {
        v2 = l2.val;
        l2 = l2.next;
      }

      int curVal = (v1 + v2 + up) % 10;
      up = (v1 + v2 + up) / 10;
      cur.next = new ListNode(curVal);
      cur = cur.next;
    }

    return dummyHead.next;
  }
}

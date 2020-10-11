package leetcode;

import java.util.Stack;

/**
 * @Description: 括号的最大嵌套深度
 * @Date 2020/10/11 15:55
 * @auther Draymonder
 */
public class Solution5535 {

  public int maxDepth(String s) {
    int res = 0;
    Stack<Character> stack = new Stack<>();

    for (int i=0; i<s.length(); i++) {
      char c = s.charAt(i);
      if (c == '(') {
        stack.push(c);
        res = Math.max(res, stack.size());
      } else if (c == ')') {
        if (!stack.empty())
          stack.pop();
        else
          throw new RuntimeException("非法字符串");
      }
    }
    return res;
  }
}

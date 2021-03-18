package base.jvm;

/**
 * 测试栈深度 -Xss256K
 */
public class StackDeepMain {

  private static int count = 0;

  private static void recur() {
    count++;
    recur();
  }


  public static void main(String[] args) {
    try {
      recur();
    } catch (Throwable e) {
      System.out.println("deep of calling " + count);
      e.printStackTrace();
    }
  }
}

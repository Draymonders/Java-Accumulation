package multi_thread.lock;

/**
 * synchronized锁学习
 *
 * @author Draymonder
 * @date 2021/02/01
 */
public class SynchronizedMain {

  private final Object object = new Object();
  private volatile int x = 0;
  private volatile static int y = 0;

  public static void main(String[] args) {
  }

  /**
   * 同步普通方法
   */
  synchronized int getMethod() {
    return ++x;
  }

  /**
   * 同步静态方法
   */
  static synchronized int staticGetMethod() {
    return ++y;
  }

  /**
   * 同步块
   */
  int getObjectMethod() {
    synchronized (this.object) {
      x ++;
    }
    return x;
  }
}

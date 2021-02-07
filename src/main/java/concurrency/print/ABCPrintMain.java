package concurrency.print;

import lombok.SneakyThrows;

/**
 * 循环打印a,b,c
 *
 * @author draymonder
 */
public class ABCPrintMain {


  public static void main(String[] args) throws InterruptedException {
    // 定义锁和同步状态
    ABCLoopState state = new ABCLoopState();
    Object lock = new Object();

    Thread a = new PrintThread('A', state, lock);
    Thread b = new PrintThread('B', state, lock);
    Thread c = new PrintThread('C', state, lock);
    c.start();
    b.start();
    a.start();
    a.join();
    b.join();
    c.join();
  }
}

class ABCLoopState {

  public volatile int v = 0;

}

class PrintThread extends Thread {

  private volatile ABCLoopState state;
  private final Object LOCK;
  private char ch;

  PrintThread(char ch, ABCLoopState state, Object lock) {
    this.ch = ch;
    this.state = state;
    this.LOCK = lock;
  }

  private int needStateByChar() {
    return ch == 'A' ? 0 : ch == 'B' ? 1 : 2;
  }

  private int nextStateByChar() {
    return ch == 'A' ? 1 : ch == 'B' ? 2 : 0;
  }

  @SneakyThrows
  @Override
  public void run() {
    while (true) {
      if (needStateByChar() == state.v) {
        synchronized (LOCK) {
          while (state.v != needStateByChar()) {
            LOCK.wait();
          }
          Thread.sleep(1000);
          System.out.println(ch);
          this.state.v = nextStateByChar();
          LOCK.notifyAll();
        }
      }
    }
  }
}

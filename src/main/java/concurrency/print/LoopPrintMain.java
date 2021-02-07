package concurrency.print;

import lombok.SneakyThrows;

/**
 * 循环打印1...100
 */
public class LoopPrintMain {

  public static void main(String[] args) throws InterruptedException {
    ShareState state = new ShareState(1);
    Object lock = new Object();

    Thread oddThread = new LoopThread(true, state, lock);
    Thread evenThread = new LoopThread(false, state, lock);
    oddThread.start();
    evenThread.start();
    oddThread.join();
    evenThread.join();
  }
}

class ShareState {

  volatile int v;

  ShareState(int v) {
    this.v = v;
  }
}

class LoopThread extends Thread {

  private boolean isOdd;
  private volatile ShareState state;
  private final Object LOCK;

  LoopThread(boolean isOdd, ShareState state, Object lock) {
    this.isOdd = isOdd;
    this.LOCK = lock;
    this.state = state;
    if (isOdd) {
      setName("odd-thread");
    } else {
      setName("even-thread");
    }
  }

  private boolean canPrint() {
    return isOdd ? state.v % 2 == 1 : state.v % 2 == 0;
  }

  @SneakyThrows
  @Override
  public void run() {
    while (state.v <= 100) {
      if (canPrint()) {
        synchronized (LOCK) {
          while (!canPrint()) {
            LOCK.wait();
          }
          // Thread.sleep(1000);
          System.out.println(String.format("%s, %d", currentThread().getName(), state.v));
          state.v++;
          LOCK.notifyAll();
        }
      }
    }
  }
}

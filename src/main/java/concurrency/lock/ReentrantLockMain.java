package concurrency.lock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

/**
 * reentrantLock学习
 *
 * @author Draymonder
 * @date 2021/02/01
 */
public class ReentrantLockMain {

  public static void main(String[] args) {
    testUse();
  }

  static void testUse() {
    ReentrantLock lock = new ReentrantLock();
    AtomicInteger y = new AtomicInteger();
    int n = 10000;
    ExecutorService threadPool = Executors.newFixedThreadPool(4);
    SimpleJob simpleJob = new SimpleJob(lock);
    for (int i=0; i<n; i++) {
      threadPool.submit(simpleJob);
    }
    try {
      Thread.sleep(60 * 1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.out.println(simpleJob.getX());
    threadPool.shutdown();
  }

  static class SimpleJob implements Runnable {
    // volatile int x = 0;
    int x = 0;
    ReentrantLock lock;

    SimpleJob(ReentrantLock lock) {
      this.lock = lock;
      x = 0;
    }

    @Override
    public void run() {
      lock.lock();
      try {
        x ++;
      } finally {
        lock.unlock();
      }
    }

    int getX() {
      return x;
    }
  }
}

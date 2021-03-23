package concurrency.print;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import lombok.SneakyThrows;

/**
 * 多线程循环打印 123...n
 *
 * @author Draymonder
 * @date 2021/03/10
 */
public class NumberLoopPrintMain {

  static volatile SharedObject[] states;

  public static void main(String[] args) {
    int n = 3;
    int maxLoopCount = 10;
    printLoopNumber(n, maxLoopCount);
  }

  @SneakyThrows
  public static void printLoopNumber(int n, int maxLoopCount) {
    AtomicInteger counter = new AtomicInteger();

    states = new SharedObject[n];
    for (int i = 0; i < n; i++) {
      states[i] = new SharedObject(0, new Object());
    }

    states[0].i = 1;

    List<Thread> threadList = new ArrayList<>();
    for (int i = 0; i < n; i++) {
      int next = (i + 1) % n;
      threadList.add(
          new Thread(new PrintNumberThread(states[i], states[next], n, i, maxLoopCount, counter)));
    }

    for (Thread t : threadList) {
      t.start();
    }
    for (Thread t : threadList) {
      t.join();
    }
  }
}

class SharedObject {

  public final Object LOCK;
  public volatile int i;

  SharedObject(int i, Object obj) {
    this.i = i;
    this.LOCK = obj;
  }
}

class PrintNumberThread implements Runnable {

  volatile SharedObject curState;
  volatile SharedObject nextState;
  final int n;
  final int cur;
  final int maxLoopCount;
  volatile AtomicInteger counter;

  PrintNumberThread(SharedObject curState, SharedObject nextState, int n, int cur, int maxLoopCount,
      AtomicInteger counter) {
    this.curState = curState;
    this.nextState = nextState;
    this.n = n;
    this.cur = cur;
    this.maxLoopCount = maxLoopCount;
    this.counter = counter;
  }

  @SneakyThrows
  @Override
  public void run() {
    while (true) {
      synchronized (curState.LOCK) {
        if (counter.get() > maxLoopCount) {
          break;
        }
        while (curState.i == 0) {
          curState.LOCK.wait();
        }
        curState.i = 0;

        int runningCount = counter.incrementAndGet();
        if (runningCount > maxLoopCount) {
          break;
        }

        System.out.println(cur);

        synchronized (nextState.LOCK) {
          nextState.i = 1;
          nextState.LOCK.notifyAll();
        }
        Thread.sleep(100);
      }
    }
  }
}
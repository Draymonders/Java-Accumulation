package concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;
import lombok.SneakyThrows;

/**
 * 生产者消费者模型
 *
 * @author yubing.zhang
 */
public class ProduceAndConsumeMain {

  Lock lock = new ReentrantLock();
  Condition notFull = lock.newCondition();
  Condition notEmpty = lock.newCondition();


  Object[] items = new Object[100];
  int putPos, takePos, count;

  @SneakyThrows
  public void put(Object x) {
    lock.lock();
    try {
      while (count == items.length) {
        notFull.await();
      }
      items[putPos] = x;
      if (++putPos == items.length) {
        putPos = 0;
      }
      ++count;
      notEmpty.signal();
    } finally {
      lock.unlock();
    }
  }

  @SneakyThrows
  public Object get() {
    lock.lock();
    try {
      while (count == 0) {
        notEmpty.await();
      }
      Object x = items[takePos];
      if (++takePos == items.length) {
        takePos = 0;
      }

      --count;
      notFull.signal();
      return x;
    } finally {
      lock.unlock();
    }
  }

  @SneakyThrows
  public static void main(String[] args) {
    ProduceAndConsumeMain queue = new ProduceAndConsumeMain();

    List<Thread> threadList = new ArrayList<>(2);
    int n = 1000;
    Thread a = new Thread(()-> {
      for (int i=0; i<n; i++) {
        queue.put(Integer.valueOf(i));
      }
    });
    Thread b = new Thread(() -> {
      for (int i=0; i<n; i++) {
        System.out.printf("%d ", queue.get());;
      }
    });
    threadList.add(a);
    threadList.add(b);
    threadList.forEach(Thread::start);
    threadList.forEach(thread -> {
      try {
        thread.join();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    });
  }


}

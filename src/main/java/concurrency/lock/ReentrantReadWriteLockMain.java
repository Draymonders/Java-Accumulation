package concurrency.lock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock;

/**
 * 读写锁
 *
 * @author Draymonder
 */
public class ReentrantReadWriteLockMain {

  public static void main(String[] args) throws InterruptedException {
    ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    ExecutorService threadPool = Executors.newFixedThreadPool(4);

    for (int i = 0; i < 3; i++) {
      AtomicInteger ai = new AtomicInteger(i + 1);
      threadPool.submit(() -> {
        ReadLock readLock = lock.readLock();
        readLock.lock();
        try {
          System.out.println("Thread " + ai.get() + " get shared Lock");
        } catch (Exception e) {
        } finally {
          readLock.unlock();
        }
      });
    }

    threadPool.submit(() -> {
      WriteLock writeLock = lock.writeLock();
      writeLock.lock();
      try {
        System.out.println("get write lock");
        Thread.sleep(5 * 1000);
      } catch (Exception e) {
      } finally {
        writeLock.unlock();
      }
    });

    Thread.sleep(6 * 1000);
    threadPool.shutdown();
  }

}

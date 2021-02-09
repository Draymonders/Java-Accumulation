package concurrency.lock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class CASMain {

  public static void main(String[] args) throws InterruptedException {
    AtomicInteger ai = new AtomicInteger();
    ExecutorService executorService = Executors.newFixedThreadPool(8);
    for (int i=0; i< 20; i++) {
      executorService.submit(() -> {
        ai.addAndGet(1);
      });
    }
    Thread.sleep(2*1000);
    executorService.shutdown();
    System.out.println(ai.get());
  }
}

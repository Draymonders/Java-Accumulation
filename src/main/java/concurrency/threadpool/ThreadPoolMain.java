package concurrency.threadpool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author Draymonder
 * @date 2021/02/23
 */
public class ThreadPoolMain {

  public static void main(String[] args) throws ExecutionException, InterruptedException {
    ExecutorService executor = Executors.newFixedThreadPool(1);
    List<Future<?>> futureList = new ArrayList<>();
    int n = 2;
    for (int i = 0; i < n; i++) {
      Future<?> future = executor.submit(() -> {
        System.out.println(Thread.currentThread().getName());
        int v = 5 / 0;
        System.out.println(Thread.currentThread().getName() + " " + v);
      });
      futureList.add(future);
    }
    for (Future<?> future : futureList) {
      future.get();
    }
    executor.shutdown();
  }
}

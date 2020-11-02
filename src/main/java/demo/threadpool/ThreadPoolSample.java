package demo.threadpool;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolSample {

  public static void main(String[] args) {
    fixedThreadPoolSample();
  }

  /**
   * 新建fixedThreadPool
   */
  public static void fixedThreadPoolSample() {
    ExecutorService executorService = Executors.newFixedThreadPool(3);
    executorService.submit(() -> {
      System.out.println("haha");
    });
    executorService.shutdown();
  }

}

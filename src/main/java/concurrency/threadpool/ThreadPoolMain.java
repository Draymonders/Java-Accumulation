package concurrency.threadpool;

import java.util.concurrent.Executors;

/**
 * @author Draymonder
 * @date 2021/02/23
 */
public class ThreadPoolMain {

  public static void main(String[] args) {
    Executors.newFixedThreadPool(2);
    Executors.newCachedThreadPool();
    Executors.newSingleThreadExecutor();
  }
}

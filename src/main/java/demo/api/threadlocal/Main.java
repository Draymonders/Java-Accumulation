package demo.api.threadlocal;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {

  private static String[] COLORS = new String[]{"blue", "yellow", "red", "black", "green"};


  public static void main(String[] args) {
    ExecutorService executor = Executors.newCachedThreadPool();
    for (int i = 0; i < 10; i++) {
      AtomicInteger id = new AtomicInteger(i);

      executor.execute(() -> {
        mark();
        try {
          Thread.sleep(2000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        show(id.get());
      });
    }
    executor.shutdown();
  }

  public static void mark() {
    int markIndex = new Random().nextInt(5);
    TContext.getContext().setMark(markIndex + "-" + COLORS[markIndex]);
  }

  public static void show(int id) {
    System.out.println("thread-id: " + id + " mark: " + TContext.getContext().getMark());
  }
}

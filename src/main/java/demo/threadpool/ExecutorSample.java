package demo.threadpool;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.Executor;
import java.util.stream.IntStream;

class SerialExecutor implements Executor {

  final Queue<Runnable> tasks = new ArrayDeque<>();
  final Executor executor;
  Runnable active;

  SerialExecutor(Executor executor) {
    this.executor = executor;
  }

  public synchronized void execute(Runnable r) {
    tasks.add(() -> {
      try {
        r.run();
      } finally {
        scheduleNext();
      }
    });
    if (active == null) {
      scheduleNext();
    }
  }

  protected synchronized void scheduleNext() {
    if ((active = tasks.poll()) != null) {
      executor.execute(active);
    }
  }
}

public class ExecutorSample {

  public static void main(String[] args) {
    debugSerialExecutor();
  }

  public static void debugSerialExecutor() {
    SerialExecutor serialExecutor = new SerialExecutor(Runnable::run);

    List<Thread> threads = new ArrayList<>(10);
    IntStream.range(0, 10).forEach(i -> {
      threads.add(new Thread(() -> {
        serialExecutor.execute(() -> {
          System.out.println(i);
        });
      }));
    });
    threads.forEach(Thread::start);
  }
}

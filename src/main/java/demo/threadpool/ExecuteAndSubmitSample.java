package demo.threadpool;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * simple worker: 延时后输出outputStr
 */
class SimpleWorker implements Runnable {

  String outputStr;

  private SimpleWorker() {}

  public SimpleWorker(String outputStr) {
    this.outputStr = outputStr;
  }

  @Override
  public void run() {
    try {
      Thread.sleep(3000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.out.println(Thread.currentThread() + " -> " + this.outputStr);
    // throw new RuntimeException("runTimeException");
  }
}

class ExceptionWorker implements Runnable {

  String outputStr;

  private ExceptionWorker() {}

  public ExceptionWorker(String outputStr) {
    this.outputStr = outputStr;
  }

  @Override
  public void run() {
    try {
      Thread.sleep(3000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.out.println(Thread.currentThread() + " -> " + this.outputStr);
    throw new RuntimeException("worker occur runtimeException");
  }
}


public class ExecuteAndSubmitSample {

  /**
   * 调用一下 newFixedTheadPool的 execute和submit方法
   */
  public static void simpleExecuteAndSumbit() {
    ExecutorService executorService = Executors.newFixedThreadPool(2);
    executorService.execute(new SimpleWorker("hello"));
    Future<?> res2 = executorService.submit(new SimpleWorker("world"));
    executorService.execute(new SimpleWorker("task3"));
    try {
      System.out.println(res2.get());
    } catch (InterruptedException | ExecutionException e) {
      e.printStackTrace();
    }
    System.out.println("ye~~");
    executorService.shutdown();
  }

  /**
   * 通过 threadPool的submit方法获取结果
   */
  public static void getResultThroughSumbit() {
    ExecutorService executorService = Executors.newFixedThreadPool(2);
    Future<String> res = executorService.submit(() -> {
      System.out.println("submit callable");
      Thread.sleep(3000);
      return "233";
    });
    // res.cancel(false);
    /**
     * 执行两遍，最终只执行了一次代码
     */
    try {
      System.out.println(res.get());
    } catch (InterruptedException | ExecutionException e) {
      e.printStackTrace();
    }
    System.out.println(System.currentTimeMillis());
    try {
      System.out.println(res.get());
    } catch (InterruptedException | ExecutionException e) {
      e.printStackTrace();
    }
    System.out.println(System.currentTimeMillis());
    executorService.shutdown();
  }

  /**
   * submit 获取任务超时
   */
  public static void submitTimeout() {
    ExecutorService executorService = Executors.newFixedThreadPool(2);
    Future<String> future = executorService.submit(() -> {
      System.out.println("submit callable");
      Thread.sleep(5000);
      return "233";
    });
    try {
      String res = future.get(2, TimeUnit.SECONDS);
      System.out.println(res);
    } catch (InterruptedException | ExecutionException e) {
      e.printStackTrace();
    } catch (TimeoutException e) {
      e.printStackTrace();
      System.out.println("get task result timeout");
    }
    executorService.shutdown();
  }

  /**
   * submit在队列中，还没线程池去接收也会timeout了
   */
  public static void submitTimeoutInQueue() {
    ExecutorService executorService = Executors.newFixedThreadPool(1);
    Future<String> future1 = executorService.submit(() -> {
      System.out.println("submit 1");
      Thread.sleep(10000);
      return "233";
    });
    System.out.println("future1");
    Future<String> future2 = executorService.submit(() -> {
      System.out.println("submit 2");
      Thread.sleep(3000);
      return "244";
    });
    System.out.println("future2");

    /**
     * 这里看到的实际结果表明, get会报timeout, 但是线程还是会继续执行
     */
    try {
      System.out.println(future2.get(1, TimeUnit.SECONDS));
    } catch (InterruptedException e) {
      e.printStackTrace();
    } catch (ExecutionException e) {
      e.printStackTrace();
    } catch (TimeoutException e) {
      e.printStackTrace();
    }
    executorService.shutdown();
  }

  public static void executeSample() {
    ExecutorService executorService = Executors.newFixedThreadPool(2);
    executorService.execute(() -> {
      try {
        Thread.sleep(2000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      System.out.println("execute 1");
    });
    executorService.execute(() -> {
      try {
        Thread.sleep(2000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      System.out.println("execute 2");
    });
    System.out.println("execute");
    executorService.shutdown();
  }

  /**
   * execute时遇到异常
   */
  public static void executeWhenException() {
    ExecutorService executorService = Executors.newFixedThreadPool(2);
    executorService.execute(new ExceptionWorker("hello"));
    /**
     * 验证线程出异常以后 还能不能执行
     */
    executorService.execute(new SimpleWorker("one"));
    executorService.execute(new SimpleWorker("two"));
    executorService.shutdown();
  }

  /**
   * submit时遇到异常
   */
  public static void submitWhenException() {
    ExecutorService executorService = Executors.newFixedThreadPool(2);
    /**
     * 根据结果推断, submit不执行future.get()，是获取不到exception的
     */
    Future<?> future = executorService.submit(new ExceptionWorker("hello"));
    /**
     * 验证线程出异常以后 还能不能执行
     */
    executorService.submit(new SimpleWorker("one"));
    executorService.submit(new SimpleWorker("two"));
    /*
    try {
      System.out.println(future.get());
    } catch (InterruptedException e) {
      e.printStackTrace();
    } catch (ExecutionException e) {
      System.out.println("submit occur error");
      e.printStackTrace();
    }
     */
    executorService.shutdown();
  }

  public static void main(String[] args) {
    // simpleExecuteAndSumbit();
    // getResultThroughSumbit();
    // submitTimeout();
    // submitTimeoutInQueue();
    // executeSample();
    // executeWhenException();
    submitWhenException();
  }
}

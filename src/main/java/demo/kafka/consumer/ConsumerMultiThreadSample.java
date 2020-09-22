package demo.kafka.consumer;

import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor.AbortPolicy;
import java.util.concurrent.TimeUnit;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;

/**
 * @Description: kafka 多线程demo
 * @Date 2020/09/19 09:07
 * @auther Draymonder
 */
public class ConsumerMultiThreadSample {

  public static void main(String[] args) throws InterruptedException {
    // 本质上创建一个线程池,用一个consumer接收信息, 然后分发给线程池里面的worker去操作record
    Executor executor = new Executor();
    executor.execute(5);

    Thread.sleep(3000);

    executor.shutdown();
  }
}

/**
 * 用一个record和线程池去消费record
 */
class Executor {
  private Consumer<String, String> consumer;
  private ExecutorService executors;

  public Executor() {
    this.consumer = ConsumerSample.consumer(false);
    consumer.subscribe(List.of(ConsumerSample.TOPIC_NAME));
  }

  public void execute(int workerNum) {
    executors = new ThreadPoolExecutor(workerNum, workerNum, 0L, TimeUnit.SECONDS, new ArrayBlockingQueue<>(1000), new AbortPolicy());

    while (true) {
      ConsumerRecords<String, String> records = consumer.poll(1000);
      for (final ConsumerRecord record : records) {
        executors.submit(new Worker(record));
      }
    }
  }

  public void shutdown() {
    if (consumer != null) {
      consumer.close();
    }
    if (executors != null) {
      executors.shutdown();
    }
    try {
      if (!executors.awaitTermination(3, TimeUnit.SECONDS)) {
        System.out.println("Timeout.... Ignore for this case");
      }
    } catch (InterruptedException ignored) {
      System.out.println("Other thread interrupted this shutdown, ignore for this case.");
      Thread.currentThread().interrupt();
    }
  }
}

/**
 * 消费record的线程
 */
class Worker implements Runnable {

  private ConsumerRecord<String, String> record;

  private Worker() {}

  public Worker(ConsumerRecord<String, String> record) {
    this.record = record;
  }

  @Override
  public void run() {
    System.out.printf("partion = %d, offset = %d, key = %s, value = %s%n", record.partition(),
        record.offset(), record.key(), record.value());
  }
}

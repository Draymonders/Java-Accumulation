package demo.kafka.producer;

import demo.kafka.admin.AdminSample;
import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

public class ProducerSample {

  public static final String TOPIC_NAME = AdminSample.TOPIC_NAME;

  public static final String SERVER = "10.40.58.64:9092";

  public static void main(String[] args) throws Exception {
    // sendDataSync();

    // sendDataAsync();

    // sendDataAsyncWithCallback();

    sendDataAsyncWithCallbackAndLoadbalance();
  }

  /**
   * 异步发送数据，带回调，带负载均衡
   */
  public static void sendDataAsyncWithCallbackAndLoadbalance()
      throws ExecutionException, InterruptedException {
    Producer<String, String> producer = producer(true);

    for (int id=1000; id<=1020; id++) {
      System.out.println("send data idx: " + id);
      ProducerRecord<String, String> producerRecord = new ProducerRecord<>(TOPIC_NAME,
          String.valueOf(id), String.valueOf(id));
      Future<RecordMetadata> recordMetadataFuture = producer.send(producerRecord);
      RecordMetadata recordMetadata = recordMetadataFuture.get();
      System.out.println(String
          .format("send data %d:  partition %s offset %d", id, recordMetadata.partition(),
              recordMetadata.offset()));
    }

    producer.close();
  }

  /**
   * 其实就是异步发送数据，然后阻塞等待数据返回
   */
  public static void sendDataSync() throws ExecutionException, InterruptedException {
    Producer<String, String> producer = producer(false);

    for (int id = 1; id <= 10; id++) {
      System.out.println("send data idx: " + id);
      ProducerRecord<String, String> producerRecord = new ProducerRecord<>(TOPIC_NAME,
          String.valueOf(id), String.valueOf(id));
      Future<RecordMetadata> recordMetadataFuture = producer.send(producerRecord);
      RecordMetadata recordMetadata = recordMetadataFuture.get();
      System.out.println(String
          .format("send data %d:  partition %s offset %d", id, recordMetadata.partition(),
              recordMetadata.offset()));
    }

    producer.close();
  }

  /**
   * 异步发送数据
   */
  public static void sendDataAsync() {
    Producer<String, String> producer = producer(false);

    for (int id = 11; id <= 20; id++) {
      System.out.println("send data idx: " + id);
      ProducerRecord<String, String> producerRecord = new ProducerRecord<>(TOPIC_NAME,
          String.valueOf(id), String.valueOf(id));
      producer.send(producerRecord);
      // RecordMetadata recordMetadata = recordMetadataFuture.get();
      // System.out.println(String.format("send data %d:  partition %s offset %d", id, recordMetadata.partition(), recordMetadata.offset()));
    }
    producer.close();
  }

  /**
   * 异步发送数据带回调
   */
  public static void sendDataAsyncWithCallback() throws Exception {
    Producer<String, String> producer = producer(false);
    for (int id = 300; id <= 320; id++) {
      System.out.println("send data idx: " + id);
      ProducerRecord<String, String> producerRecord = new ProducerRecord<>(TOPIC_NAME,
          String.valueOf(id), String.valueOf(id));
      String idx = String.valueOf(id);
      producer.send(producerRecord, (metadata, exception) -> {
        if (exception == null) {
          System.out.println(String
              .format("send data %s:  partition %s offset %d", idx, metadata.partition(),
                  metadata.offset()));
        }
        else {
          throw new RuntimeException("send data error id: " + idx, exception);
        }
      });
    }
    producer.close();
  }

  /**
   * 创建producer, loadBalance策略 todo 配置内容学习
   */
  public static Producer<String, String> producer(boolean loadBalance) {
    Properties properties = new Properties();
    properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, SERVER);
    properties.put(ProducerConfig.ACKS_CONFIG, "all");
    properties.put(ProducerConfig.RETRIES_CONFIG, "0");
    properties.put(ProducerConfig.BATCH_SIZE_CONFIG, "16384");
    properties.put(ProducerConfig.LINGER_MS_CONFIG, "1");
    properties.put(ProducerConfig.BUFFER_MEMORY_CONFIG, "33554432");

    properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
        "org.apache.kafka.common.serialization.StringSerializer");
    properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
        "org.apache.kafka.common.serialization.StringSerializer");
    if (loadBalance) {
      properties.put(ProducerConfig.PARTITIONER_CLASS_CONFIG, "demo.kafka.producer.PartitionSample");
    }
    return new KafkaProducer<>(properties);
  }
}

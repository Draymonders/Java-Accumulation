package demo.kafka.consumer;

import demo.kafka.admin.AdminSample;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.TopicPartition;

/**
 * @Description:
 * @Date 2020/09/16 23:04
 * @auther Draymonder
 */
public class ConsumerSample {

  public static final String TOPIC_NAME = AdminSample.TOPIC_NAME;

  public static final String SERVER = AdminSample.SERVER;


  public static void main(String[] args) {
    // autoCommitOffset();

    // manualCommitOffset(true);

    // manualCommitOffsetWithPartition();

    // manualCommmitOffsetWithSelectedPartition();

    controlOffset();
  }

  /**
   * 控制offset位置
   */
  public static void controlOffset() {
    KafkaConsumer<String, String> consumer = consumer(false);
    consumer.subscribe(List.of(TOPIC_NAME));

    try {
      while (true) {
        ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(3000));

        // 获取每个partition的消息
        for (TopicPartition topicPartition : records.partitions()) {
          List<ConsumerRecord<String, String>> partitionRecords = records.records(topicPartition);
          long lastOffset = -1;
          for (ConsumerRecord<String, String> partitionRecord : partitionRecords) {
            System.out.printf("partion = %d, offset = %d, key = %s, value = %s%n",
                partitionRecord.partition(),
                partitionRecord.offset(), partitionRecord.key(), partitionRecord.value());
            lastOffset = partitionRecord.offset();
          }
          // 用于提交offset信息
          Map<TopicPartition, OffsetAndMetadata> partition2Offset = new HashMap<>();
          String partitionName = "partition-" + topicPartition.partition();
          if (lastOffset != -1) {
            partition2Offset
                .put(topicPartition, new OffsetAndMetadata(lastOffset + 1, partitionName));
            consumer.commitAsync(partition2Offset, (offsets, exception) -> {
              if (null == exception) {
                System.err.println(partitionName + " 消费完毕");
              }
            });
          }
        }
      }
    } catch (Exception e) {
      consumer.close();
    }
  }


  /**
   * 手动提交offset, 只接收被选的partition
   */
  public static void manualCommmitOffsetWithSelectedPartition() {
    KafkaConsumer<String, String> consumer = consumer(false);
    TopicPartition partition0 = new TopicPartition(TOPIC_NAME, 0);
//    TopicPartition partition1 = new TopicPartition(TOPIC_NAME, 1);
//    TopicPartition partition2 = new TopicPartition(TOPIC_NAME, 2);
    TopicPartition partition3 = new TopicPartition(TOPIC_NAME, 3);

    consumer.assign(List.of(partition0, partition3));

    try {
      while (true) {
        ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(3000));

        // 获取每个partition的消息
        for (TopicPartition topicPartition : records.partitions()) {
          List<ConsumerRecord<String, String>> partitionRecords = records.records(topicPartition);
          long lastOffset = -1;
          for (ConsumerRecord<String, String> partitionRecord : partitionRecords) {
            System.out.printf("partion = %d, offset = %d, key = %s, value = %s%n",
                partitionRecord.partition(),
                partitionRecord.offset(), partitionRecord.key(), partitionRecord.value());
            lastOffset = partitionRecord.offset();
          }
          // 用于提交offset信息
          Map<TopicPartition, OffsetAndMetadata> partition2Offset = new HashMap<>();
          String partitionName = "partition-" + topicPartition.partition();
          if (lastOffset != -1) {
            partition2Offset
                .put(topicPartition, new OffsetAndMetadata(lastOffset + 1, partitionName));
            consumer.commitAsync(partition2Offset, (offsets, exception) -> {
              if (null == exception) {
                System.out.println(partitionName + " 消费完毕");
              }
            });
          }
        }
      }
    } catch (Exception e) {
      consumer.close();
    }
  }


  /**
   * 手动提交offset 根据partition来进行推送
   */
  public static void manualCommitOffsetWithPartition() {
    KafkaConsumer<String, String> consumer = consumer(false);
    consumer.subscribe(List.of(TOPIC_NAME));
    try {
      while (true) {
        ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(3000));

        // 获取每个partition的消息
        for (TopicPartition topicPartition : records.partitions()) {
          List<ConsumerRecord<String, String>> partitionRecords = records.records(topicPartition);
          long lastOffset = -1;
          for (ConsumerRecord<String, String> partitionRecord : partitionRecords) {
            System.out.printf("partion = %d, offset = %d, key = %s, value = %s%n",
                partitionRecord.partition(),
                partitionRecord.offset(), partitionRecord.key(), partitionRecord.value());
            lastOffset = partitionRecord.offset();
          }
          // 用于提交offset信息
          Map<TopicPartition, OffsetAndMetadata> partition2Offset = new HashMap<>();
          String partitionName = "partition-" + topicPartition.partition();
          if (lastOffset != -1) {
            partition2Offset
                .put(topicPartition, new OffsetAndMetadata(lastOffset + 1, partitionName));
            consumer.commitAsync(partition2Offset, (offsets, exception) -> {
              if (null == exception) {
                System.out.println(partitionName + " 消费完毕");
              }
            });
          }
        }
      }

    } catch (Exception e) {
      consumer.close();
    }
  }

  /**
   * 手动提交offset 如果isCommitOffset为false, 那么每次重启执行manualCommitOffset都会读取相同的数据
   */
  public static void manualCommitOffset(boolean isCommitOffset) {
    KafkaConsumer<String, String> consumer = consumer(false);
    consumer.subscribe(List.of(TOPIC_NAME));
    try {
      while (true) {
        ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(10000));
        for (ConsumerRecord<String, String> record : records) {
          System.out.printf("partion = %d, offset = %d, key = %s, value = %s%n", record.partition(),
              record.offset(), record.key(), record.value());
        }
        if (isCommitOffset) {
          consumer.commitAsync();
        }
      }
    } catch (Exception e) {
      consumer.close();
    }
  }

  /**
   * 自动提交offset
   */
  public static void autoCommitOffset() {
    KafkaConsumer<String, String> consumer = consumer(true);
    consumer.subscribe(List.of(TOPIC_NAME));
    try {
      while (true) {
        ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(10000));
        for (ConsumerRecord<String, String> record : records) {
          System.out.printf("partion = %d, offset = %d, key = %s, value = %s%n", record.partition(),
              record.offset(), record.key(), record.value());
        }
      }
    } catch (Exception e) {
      consumer.close();
    }
  }


  public static KafkaConsumer<String, String> consumer(boolean autoCommit) {
    Properties props = new Properties();
    props.setProperty("bootstrap.servers", SERVER);
    props.setProperty("group.id", "manual_commit_offset");
    props.setProperty("enable.auto.commit", autoCommit ? "true" : "false");
    if (autoCommit) {
      props.setProperty("auto.commit.interval.ms", "1000");
    }
    props.setProperty("key.deserializer",
        "org.apache.kafka.common.serialization.StringDeserializer");
    props.setProperty("value.deserializer",
        "org.apache.kafka.common.serialization.StringDeserializer");
    return new KafkaConsumer<>(props);
  }
}

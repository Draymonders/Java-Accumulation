package demo.kafka.simple;

import java.util.Arrays;
import java.util.Properties;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

public class SimpleKafkaConsumer {

  private static final String topicName = "user-logs";

  public static void main(String[] args) {
    Properties props = new Properties();

    props.put("bootstrap.servers", "10.40.58.64:9092");
    props.put("group.id", "just-test");
    props.put("enable.auto.commit", "true");
    props.put("auto.commit.interval.ms", "1000");
    props.put("session.timeout.ms", "30000");
    props.put("key.deserializer",
        "org.apache.kafka.common.serialization.StringDeserializer");
    props.put("value.deserializer",
        "org.apache.kafka.common.serialization.StringDeserializer");
    KafkaConsumer<String, String> consumer = new KafkaConsumer
        <>(props);
    consumer.subscribe(Arrays.asList(topicName));
    System.out.println("Subscribed to topic "  + topicName);
    int i = 0;
    while (i < 10) {
      ConsumerRecords<String, String> records = consumer.poll(1000);
      for (ConsumerRecord<String, String> record : records)

        // print the offset,key and value for the consumer records.
        System.out.printf("offset = %d, key = %s, value = %s\n",
            record.offset(), record.key(), record.value());
      i ++;
    }
  }
}

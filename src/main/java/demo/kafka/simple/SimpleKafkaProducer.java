package demo.kafka.simple;

import java.util.Properties;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

public class SimpleKafkaProducer {

  private static final String topic = "user-logs";

  public static void main(String[] args) {
    Properties props = new Properties();
    props.put("bootstrap.servers", "10.40.58.64:9092");
    props.put("ack", "1");
    props.put("retries", 0);
    props.put("linger.ms", 1);
    props.put("buffer.memory", 33554432);
    props.put("key.serializer",
        "org.apache.kafka.common.serialization.StringSerializer");
    props.put("value.serializer",
        "org.apache.kafka.common.serialization.StringSerializer");

    StringSerializer stringSerializer = new StringSerializer();

    Producer<String, String> producer = new KafkaProducer<>(props);
    for (int i = 0; i < 10; i++) {
      producer.send(new ProducerRecord<>(topic, Integer.toString(i), Integer.toString(i)),
          (recordMetadata, e) -> {
            System.out.println(recordMetadata.toString());
            if (e != null) {
              System.out.println(e);
            }
          });
    }
    System.out.println("Message sent successfully");
    producer.close();
  }
}

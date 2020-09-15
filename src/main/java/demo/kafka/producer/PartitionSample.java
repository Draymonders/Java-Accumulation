package demo.kafka.producer;

import java.util.Map;
import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;

/**
 * @Description:
 * @Date 2020/09/15 22:56
 * @auther Draymonder
 */
public class PartitionSample implements Partitioner {

  @Override
  public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes,
      Cluster cluster) {
    int partionNums = 4;
    int res = Integer.parseInt(key.toString()) % partionNums;
    System.out.println("key: " + key + " partionId: " + res);
    return res;
  }

  @Override
  public void close() {

  }

  @Override
  public void configure(Map<String, ?> configs) {

  }
}

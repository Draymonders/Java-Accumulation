package demo.kafka.admin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.AlterConfigOp;
import org.apache.kafka.clients.admin.AlterConfigOp.OpType;
import org.apache.kafka.clients.admin.ConfigEntry;
import org.apache.kafka.clients.admin.CreateTopicsResult;
import org.apache.kafka.clients.admin.ListTopicsResult;
import org.apache.kafka.clients.admin.NewPartitions;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.admin.TopicDescription;
import org.apache.kafka.common.config.ConfigResource;
import org.apache.kafka.common.config.ConfigResource.Type;

/**
 * @Description: kafka admin sample
 * @Date 2020/09/13 17:58
 * @auther Draymonder
 */
public class AdminSample {

  public static final String TOPIC_NAME = "Draymonder_topic";

  public static void main(String[] args) throws Exception {
    /*
    AdminClient adminClient = adminClient();
    System.out.println("admin client: " + adminClient);
     */
    /*
    createTopic();
    listTopics();
     */
    // descTopics();
    // descConfig();

    // alterPartitionConfig();
    alterConfig();
    descConfig();
  }

  /**
   * 修改topic对应的partition个数 partition从2 -> 3
   */
  public static void alterPartitionConfig() throws ExecutionException, InterruptedException {
    AdminClient adminClient = adminClient();
    Map<String, NewPartitions> newPartitions = new HashMap<>();

    NewPartitions partition = NewPartitions.increaseTo(3);
    newPartitions.put(TOPIC_NAME, partition);
    adminClient.createPartitions(newPartitions).all().get();
  }

  /**
   * 修改配置信息, preallocate由 false -> true
   */
  public static void alterConfig() throws ExecutionException, InterruptedException {
    AdminClient adminClient = adminClient();

    Map<ConfigResource, Collection<AlterConfigOp>> configs = new HashMap<>();
    ConfigResource configResource = new ConfigResource(Type.TOPIC, TOPIC_NAME);

    List<AlterConfigOp> alterConfigOps = new ArrayList<>();
    alterConfigOps.add(new AlterConfigOp(new ConfigEntry("preallocate", "true"), OpType.SET));
    configs.put(configResource, alterConfigOps);

    adminClient.incrementalAlterConfigs(configs).all().get();
  }

  /**
   * 获取配置信息
   * resource:
   *  ConfigResource(type=TOPIC, name='Draymonder_topic')
   * config:
   *  Config (
   *  entries=[ConfigEntry(name=compression.type, value=producer, source=DEFAULT_CONFIG, isSensitive=false, isReadOnly=false, synonyms=[]),
   *    ConfigEntry(name=leader.replication.throttled.replicas, value=, source=DEFAULT_CONFIG, isSensitive=false, isReadOnly=false, synonyms=[]),
   *    ConfigEntry(name=message.downconversion.enable, value=true, source=DEFAULT_CONFIG, isSensitive=false, isReadOnly=false, synonyms=[]),
   *    ConfigEntry(name=min.insync.replicas, value=1, source=DEFAULT_CONFIG, isSensitive=false, isReadOnly=false, synonyms=[]),
   *    ConfigEntry(name=segment.jitter.ms, value=0, source=DEFAULT_CONFIG, isSensitive=false, isReadOnly=false, synonyms=[]),
   *    ConfigEntry(name=cleanup.policy, value=delete, source=DEFAULT_CONFIG, isSensitive=false, isReadOnly=false, synonyms=[]),
   *    ConfigEntry(name=flush.ms, value=9223372036854775807, source=DEFAULT_CONFIG, isSensitive=false, isReadOnly=false, synonyms=[]),
   *    ConfigEntry(name=follower.replication.throttled.replicas, value=, source=DEFAULT_CONFIG, isSensitive=false, isReadOnly=false, synonyms=[]),
   *    ConfigEntry(name=segment.bytes, value=1073741824, source=STATIC_BROKER_CONFIG, isSensitive=false, isReadOnly=false, synonyms=[]),
   *    ConfigEntry(name=retention.ms, value=86400000, source=DEFAULT_CONFIG, isSensitive=false, isReadOnly=false, synonyms=[]),
   *    ConfigEntry(name=flush.messages, value=9223372036854775807, source=DEFAULT_CONFIG, isSensitive=false, isReadOnly=false, synonyms=[]),
   *    ConfigEntry(name=message.format.version, value=2.5-IV0, source=DEFAULT_CONFIG, isSensitive=false, isReadOnly=false, synonyms=[]),
   *    ConfigEntry(name=file.delete.delay.ms, value=60000, source=DEFAULT_CONFIG, isSensitive=false, isReadOnly=false, synonyms=[]),
   *    ConfigEntry(name=max.compaction.lag.ms, value=9223372036854775807, source=DEFAULT_CONFIG, isSensitive=false, isReadOnly=false, synonyms=[]),
   *    ConfigEntry(name=max.message.bytes, value=1048588, source=DEFAULT_CONFIG, isSensitive=false, isReadOnly=false, synonyms=[]),
   *    ConfigEntry(name=min.compaction.lag.ms, value=0, source=DEFAULT_CONFIG, isSensitive=false, isReadOnly=false, synonyms=[]),
   *    ConfigEntry(name=message.timestamp.type, value=CreateTime, source=DEFAULT_CONFIG, isSensitive=false, isReadOnly=false, synonyms=[]),
   *    ConfigEntry(name=preallocate, value=false, source=DEFAULT_CONFIG, isSensitive=false, isReadOnly=false, synonyms=[]),
   *    ConfigEntry(name=min.cleanable.dirty.ratio, value=0.5, source=DEFAULT_CONFIG, isSensitive=false, isReadOnly=false, synonyms=[]),
   *    ConfigEntry(name=index.interval.bytes, value=4096, source=DEFAULT_CONFIG, isSensitive=false, isReadOnly=false, synonyms=[]),
   *    ConfigEntry(name=unclean.leader.election.enable, value=false, source=DEFAULT_CONFIG, isSensitive=false, isReadOnly=false, synonyms=[]),
   *    ConfigEntry(name=retention.bytes, value=-1, source=DEFAULT_CONFIG, isSensitive=false, isReadOnly=false, synonyms=[]),
   *    ConfigEntry(name=delete.retention.ms, value=86400000, source=DEFAULT_CONFIG, isSensitive=false, isReadOnly=false, synonyms=[]),
   *    ConfigEntry(name=segment.ms, value=604800000, source=DEFAULT_CONFIG, isSensitive=false, isReadOnly=false, synonyms=[]),
   *    ConfigEntry(name=message.timestamp.difference.max.ms, value=9223372036854775807, source=DEFAULT_CONFIG, isSensitive=false, isReadOnly=false, synonyms=[]),
   *    ConfigEntry(name=segment.index.bytes, value=10485760, source=DEFAULT_CONFIG, isSensitive=false, isReadOnly=false, synonyms=[])])
   *
   */
  public static void descConfig() throws ExecutionException, InterruptedException {
    AdminClient adminClient = adminClient();
    ConfigResource configResource = new ConfigResource(Type.TOPIC, TOPIC_NAME);

    adminClient.describeConfigs(List.of(configResource)).all().get().forEach(((resource, config) -> {
      System.out.println("resource: " + resource + " config: " + config);
    }));
  }


  /**
   * topic 描述
   *
   * topic name:
   *  Draymonder_topic
   * desc:
   *  (name=Draymonder_topic,
   *   internal=false,
   *   partitions=(
   *    partition=0,
   *    leader=10.60.24.37:9092 (id: 1001 rack: null),
   *    replicas=10.60.24.37:9092 (id: 1001 rack: null),
   *    isr=10.60.24.37:9092 (id: 1001 rack: null)),
   *    (partition=1,
   *    leader=10.60.24.37:9092 (id: 1001 rack: null),
   *    replicas=10.60.24.37:9092 (id: 1001 rack: null),
   *    isr=10.60.24.37:9092 (id: 1001 rack: null)
   *   ),
   *  authorizedOperations=null)
   */
  public static void descTopics() throws ExecutionException, InterruptedException {
    AdminClient adminClient = adminClient();
    Map<String, TopicDescription> topicsDesc = adminClient
        .describeTopics(Arrays.asList(TOPIC_NAME)).all().get();
    topicsDesc.forEach((topicName, topicDescription) -> {
      System.out.println("topic name: " + topicName + " desc: " + topicDescription);
    });
  }


  /**
   * 获取topic list
   */
  public static void listTopics() throws ExecutionException, InterruptedException {
    AdminClient adminClient = adminClient();
    ListTopicsResult listTopicsResult = adminClient.listTopics();
    Set<String> names = listTopicsResult.names().get();
    names.forEach(System.out::println);
  }

  /**
   * 创建topic
   */
  public static void createTopic() {
    AdminClient adminClient = adminClient();

    NewTopic topic = new NewTopic(TOPIC_NAME, 2, (short) 1);
    CreateTopicsResult topicCreateResult = adminClient.createTopics(Arrays.asList(topic));
    System.out.println("create topic result: " + topicCreateResult);
  }

  /**
   * create admin client
   *
   * @return
   */
  public static AdminClient adminClient() {
    Properties props = new Properties();
    props.setProperty(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, "10.60.24.37:9092");

    AdminClient adminClient = AdminClient.create(props);
    return adminClient;
  }
}

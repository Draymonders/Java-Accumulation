package demo.serialize.json;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: json demo
 * @Date 2020/12/13 21:59
 * @auther Draymonder
 */
public class JsonSample {

  private static Map<String, Object> map = new HashMap<>();

  static {
    map.put("1", "2");
    map.put("2", null);
  }

  /**
   * map中的value为null也输出
   */
//  public static void testOutputNullValue() {
//    String res = JSON.toJSONString(map, SerializerFeature.WriteMapNullValue);
//    System.out.println(res);
//  }

  /**
   * value不为null才输出
   */
//  public static void testOutputNotNullValue() {
//    String res = JSON.toJSONString(map);
//    System.out.println(res);
//  }
  public static void main(String[] args) {
    // testOutputNullValue();
    // testOutputNotNullValue();
  }
}

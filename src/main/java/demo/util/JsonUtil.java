package demo.util;

import com.alibaba.fastjson.JSON;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonUtil {

  public static List toStrList(String jsonStr) {
    return JSON.parseArray(jsonStr);
  }

  public static List<Map> toMapList(String jsonStr) {
    List list = JSON.parseArray(jsonStr);
    List<Map> result = new ArrayList<>();

    list.forEach(item -> {
      String itemStr = item + "";
      Map map = JSON.parseObject(itemStr);
      result.add(map);
    });
    return result;
  }

  public static Map<String, List> toStr2ListMap(String jsonStr) {
    Map map = JSON.parseObject(jsonStr);
    Map<String, List> result = new HashMap<>();
    map.keySet().forEach(key -> {
      String itemStr = map.get(key) + "";
      List list = JSON.parseArray(itemStr);
      result.put((String) key, list);
    });
    return result;
  }

  public static Map toMap(String jsonStr) {
    return JSON.parseObject(jsonStr);
  }

}

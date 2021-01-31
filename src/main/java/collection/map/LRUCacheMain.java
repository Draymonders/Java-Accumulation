package collection.map;

import java.util.LinkedHashMap;

interface ILRUCache {

  int get(int key);

  void put(int key, int value);
}

/**
 * LRU实现
 *
 * @author Draymonder
 * @date 2021/01/31
 */
public class LRUCacheMain {


  public static void main(String[] args) {
    LRUCache cache = new LinkedHashMapLRUCache(2 /* 缓存容量 */);

    cache.put(1, 1);
    cache.put(2, 2);
    // cache.get(1);  返回  1
    System.out.println(cache.get(1) == 1);
    cache.put(3, 3);
    // 该操作会使得密钥 2 作废
    cache.get(2);
    // 返回 -1 (未找到)
    System.out.println(cache.get(2) == -1);
    cache.put(4, 4);
    // 该操作会使得密钥 1 作废
    // cache.get(1);
    // 返回 -1 (未找到)
    System.out.println(cache.get(1) == -1);
    // cache.get(3);
    // 返回  3
    System.out.println(cache.get(3) == 3);
    cache.get(4);
  }

  /**
   * linkedHashMap实现的LRUCache
   */
  static class LinkedHashMapLRUCache extends LRUCache {

    LinkedHashMap<Integer, Integer> cache;

    LinkedHashMapLRUCache(int capacity) {
      super(capacity);
      cache = new LinkedHashMap<>();
    }

    @Override
    public int get(int key) {
      if (!cache.containsKey(key)) {
        return -1;
      }
      Integer val = cache.remove(key);
      cache.put(key, val);
      return val;
    }

    @Override
    public void put(int key, int value) {
      if (cache.containsKey(key)) {
        cache.remove(key);
        cache.put(key, value);
        return;
      }
      cache.put(key, value);
      if (cache.size() > cap) {
        cache.remove(cache.keySet().iterator().next());
      }
    }
  }
}

class LRUCache implements ILRUCache {

  public int cap;

  LRUCache(int capacity) {
    this.cap = capacity;
  }

  @Override
  public int get(int key) {
    return -1;
  }

  @Override
  public void put(int key, int value) {
  }

}
package demo.pattern.singleton;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 双重检验 懒汉单例实现
 * @Date 2020/08/22 11:12
 * @auther Draymonder
 */
public class LazyDoubleCheckSingleton {

  // volatile 和 final 不兼容
  private volatile static LazyDoubleCheckSingleton instance;

  private LazyDoubleCheckSingleton() {
  }

  public static LazyDoubleCheckSingleton getInstance() {
    if (instance == null) {
      synchronized (LazyDoubleCheckSingleton.class) {
        if (instance == null)
          /*
           1. memory = allocate(); 分配对象内存空间
           2. fieldInstance(memory); 初始化对象字段
           3. instance = memory; 设置instance指向刚分配的内存地址，此时instance != null
           上述可能会执行指令的重排序, 如 1,3,2 导致对象并未初始化完即被调用, 出现问题
           因此使用 **volatile** 来禁止指令重排序
           */ {
          instance = new LazyDoubleCheckSingleton();
        }
      }
    }
    return instance;
  }

  public static void main(String[] args) throws InterruptedException {
    int n = 10;
    List<Thread> threads = new ArrayList<>();
    for (int i = 0; i < n; i++) {
      threads.add(new Thread(() -> {
        LazyDoubleCheckSingleton instance = getInstance();
        System.out.println("instance name " + instance);
      }));
    }
    for (int i = 0; i < n; i++) {
      threads.get(i).start();
    }
    for (int i = 0; i < n; i++) {
      threads.get(i).join();
    }
  }
}

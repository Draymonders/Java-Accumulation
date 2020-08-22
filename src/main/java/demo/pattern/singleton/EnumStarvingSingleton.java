package demo.pattern.singleton;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 枚举式 单例 (防止反射注入)
 * @Date 2020/08/22 12:33
 * @auther Draymonder
 */
public class EnumStarvingSingleton {

  private EnumStarvingSingleton() {
  }

  public static EnumStarvingSingleton getInstance() {
    return EnumContainer.HOLDER.instance;
  }

  public static void main(String[] args) throws InterruptedException {
    int n = 10;
    List<Thread> threads = new ArrayList<>();
    for (int i = 0; i < n; i++) {
      threads.add(new Thread(() -> {
        EnumStarvingSingleton instance = EnumStarvingSingleton.getInstance();
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

  private enum EnumContainer {
    HOLDER;
    private EnumStarvingSingleton instance;

    EnumContainer() {
      instance = new EnumStarvingSingleton();
    }
  }
}

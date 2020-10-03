package demo.pattern.singleton;

/**
 * @Description: 饿汉式单例实现
 * @Date 2020/08/22 10:54
 * @auther Draymonder
 */
public class StarvingSingleton {

  private static final StarvingSingleton starvingSingleton = new StarvingSingleton();

  private StarvingSingleton() {
  }

  public static StarvingSingleton getInstance() {
    return starvingSingleton;
  }

  public static void main(String[] args) {
    System.out.println(getInstance());
    System.out.println(getInstance());
  }
}

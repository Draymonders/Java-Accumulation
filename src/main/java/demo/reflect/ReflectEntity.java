package demo.reflect;

public class ReflectEntity {

  public String age;
  protected long ts;
  private String name;
  private int index;

  public ReflectEntity() {
    System.out.println("这是默认构造方法");
  }

  ReflectEntity(String name) {
    this.name = name;
    System.out.println("这是构造方法1 name: " + this.name);
  }

  protected ReflectEntity(String name, int index) {
    this.index = index;
    this.name = name;
    System.out.println("这是protected 构造方法2 name: " + this.name + " index: " + this.index);
  }

  private ReflectEntity(String name, String age) {
    this.age = age;
    this.name = name;
    System.out.println("这是私有 构造方法3 name: " + this.name + " ege: " + this.age);
  }

  public ReflectEntity(String name, String age, int index, long ts) {
    this.name = name;
    this.age = age;
    this.index = index;
    this.ts = ts;
    System.out.println("这是共有 构造方法4, 初始化了4个参数");
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  private String showName(String token, int id) {
    System.out.println("token: " + token);
    System.out.println("id: " + id);
    return this.name;
  }
}

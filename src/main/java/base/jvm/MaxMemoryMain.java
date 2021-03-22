package base.jvm;

/**
 * 最大内存测试 -Xmx32m
 */
public class MaxMemoryMain {

  public static void main(String[] args) {
    for (int i = 0; i < args.length; i++) {
      System.out.println(String.format("参数 %d : %s", i + 1, args[i]));
    }
    System.out.println("-Xmx: " + Runtime.getRuntime().maxMemory() / 1000 / 1000 + "M");
  }
}

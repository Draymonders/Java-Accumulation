package demo.jvm_params;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 模拟Heap OOM
 * @Date 2020/08/23 20:11
 * @auther Draymonder
 */
public class HeapOOM {

  /**
   * vm-params: -Xms5m -Xmx5m -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/home/draymonder/Desktop/heap_oom.hprof
   * 下载一个　Visual VM 性能分析
   */
  public static void main(String[] args) {
    List<Object> list = new ArrayList<>();
    while (true)
      list.add(new Object());
  }
}

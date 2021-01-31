package collection.queue;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.stream.IntStream;

/**
 * 学习使用队列
 *
 * @author Draymonder
 * @date 2021/01/30
 */
public class QueueMain {

  public static void main(String[] args) {
    // ArrayBlockingQueueMain.testUse();
    // ArrayBlockingQueueMain.testNoDataPull();
    // ArrayBlockingQueueMain.testDataCanNotPush();
    // ArrayBlockingQueueMain.testDataTakeBlock();
    // ArrayBlockingQueueMain.testDrainTo();

    // LinkedBlockingQueueMain.testUse();

    // ArrayDequeMain.testUse();

    PriorityQueueMain.testUse();
  }


  /**
   * 优先队列
   */
  static class PriorityQueueMain {
    // TODO: 了解优先队列实现细节, 堆排序

    static void testUse() {
      PriorityQueue<Integer> queue = new PriorityQueue<>();
      IntStream.range(0, 10).forEach(queue::offer);

      System.out.println(queue.peek());
    }
  }


  /**
   * 双端数组队列
   */
  static class ArrayDequeMain {

    static void testUse() {
      Deque<Integer> deque = new ArrayDeque<>();
      deque.addFirst(1);
      deque.addLast(2);
      deque.addFirst(3);
      int res = deque.removeFirst();
      // 应该是3
      System.out.println("removeFirst: " + res);
      // 应该是2
      System.out.println("removeLast: " + deque.removeLast());
    }
  }


  /**
   * 无界队列
   */
  static class LinkedBlockingQueueMain {

    /**
     * 简单使用
     */
    static void testUse() {
      Queue<Integer> queue = new LinkedBlockingQueue<>();
      queue.offer(1);
      queue.offer(2);
      Integer res = queue.poll();
      System.out.println(res);
    }
  }

  /**
   * 有界队列
   */
  static class ArrayBlockingQueueMain {

    /**
     * 调用drainTo方法
     */
    static void testDrainTo() {
      ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<>(1);
      queue.offer(1);
      List<Integer> list = new ArrayList<>();

      // queue的数据进入list中
      queue.drainTo(list);
      System.out.println("queue isEmpty: " + queue.isEmpty());
      System.out.println(list);
    }

    /**
     * 模拟put阻塞
     */
    static void testDataPutBlock() {
      ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<>(1);
      queue.offer(1);

      try {
        queue.put(1);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }

    /**
     * 模拟take阻塞
     */
    static void testDataTakeBlock() {
      ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<>(1);
      try {
        Integer res = queue.take();
        System.out.println(res);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }

    /**
     * 队列里面数据满了, 继续加数据
     */
    static void testDataCanNotPush() {
      Queue<Integer> queue = new ArrayBlockingQueue<>(1);
      queue.offer(1);
      boolean pushSuccess = queue.offer(2);
      System.out.println("=== offer ===");
      System.out.println(pushSuccess);

      System.out.println("=== add ===");
      pushSuccess = queue.add(2);
      System.out.println(pushSuccess);
    }

    /**
     * 队列里面没有数据, 拉取数据
     */
    static void testNoDataPull() {
      Queue<Integer> queue = new ArrayBlockingQueue<>(10);
      Integer res = queue.poll();
      System.out.println("=== poll ===");
      System.out.println(res);

      System.out.println("=== remove ===");
      res = queue.remove();
      System.out.println(res);
    }

    /**
     * 简单使用
     */
    static void testUse() {
      Queue<Integer> queue = new ArrayBlockingQueue<>(10);
      queue.offer(233);
      System.out.println(queue.peek());
      Integer res = queue.poll();
      System.out.println(res);
    }
  }

}

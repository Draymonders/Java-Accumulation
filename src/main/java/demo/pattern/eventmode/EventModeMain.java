package demo.pattern.eventmode;

/**
 * @Description:
 * @Date 2020/08/26 07:31
 * @auther Draymonder
 */
public class EventModeMain {

  public static void main(String[] args) {
    EventSource eventSource = new EventSource();

    // 声明两种EventListener
    EventListener e1 = new SingleClickEventListener();
    EventListener e2 = new DoubleClickEventListener();

    // 将listener加入到eventSource
    eventSource.registerEventListener(e1);
    eventSource.registerEventListener(e2);

    // 创建两个事件
    Event ev1 = new Event(); ev1.setType("singleClick");
    Event ev2 = new Event(); ev2.setType("doubleClick");

    eventSource.publishEvent(ev1);
    eventSource.publishEvent(ev2);

  }
}

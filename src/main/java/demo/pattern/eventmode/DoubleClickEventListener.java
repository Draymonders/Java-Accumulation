package demo.pattern.eventmode;

/**
 * @Description:
 * @Date 2020/08/26 07:30
 * @auther Draymonder
 */
public class DoubleClickEventListener implements EventListener {

  @Override
  public void processEvent(Event e) {
    if ("doubleClick".equals(e.getType())) {
      System.out.println("双击了一下");
    }
  }
}

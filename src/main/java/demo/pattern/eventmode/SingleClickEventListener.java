package demo.pattern.eventmode;

/**
 * @Description:
 * @Date 2020/08/26 07:29
 * @auther Draymonder
 */
public class SingleClickEventListener implements EventListener {

  @Override
  public void processEvent(Event e) {
    if ("singleClick".equals(e.getType())) {
      System.out.println("单击了一下");
    }
  }
}

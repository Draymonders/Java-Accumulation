package demo.pattern.eventmode;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Date 2020/08/26 07:23
 * @auther Draymonder
 */
public class EventSource {
  List<EventListener> eventListenerList = new ArrayList<>();

  public void registerEventListener(EventListener eventListener) {
    eventListenerList.add(eventListener);
  }

  public void publishEvent(Event e) {
    for (EventListener listener : eventListenerList)
        listener.processEvent(e);
  }

}

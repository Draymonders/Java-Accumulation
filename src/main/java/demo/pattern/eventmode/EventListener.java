package demo.pattern.eventmode;

public interface EventListener<T> {
  void processEvent(Event e);
}

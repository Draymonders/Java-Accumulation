package demo.api.threadlocal;

public class TContext {

  private static ThreadLocal<TContext> contextThreadLocal = ThreadLocal.withInitial(TContext::new);

  private String mark;

  public static TContext getContext() {
    return contextThreadLocal.get();
  }

  public void setMark(String mark) {
    this.mark = mark;
  }

  public String getMark() {
    return this.mark;
  }
}

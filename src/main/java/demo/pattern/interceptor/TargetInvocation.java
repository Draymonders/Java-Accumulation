package demo.pattern.interceptor;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @Description:
 * @Date 2020/10/04 01:59
 * @auther Draymonder
 */
public class TargetInvocation {

  private List<Interceptor> interceptorList = new ArrayList<>();
  private Iterator<Interceptor> interceptors;

  public Target getTarget() {
    return target;
  }

  public void setTarget(Target target) {
    this.target = target;
  }

  public String getInput() {
    return input;
  }

  public void setInput(String input) {
    this.input = input;
  }

  private Target target;
  private String input;

  /**
   * 调用 反射函数
   */
  public String invoke() {
    if (interceptors.hasNext()) {
      Interceptor interceptor = interceptors.next();
      interceptor.intercept(this);
    }
    return target.execute(input);
  }


  public void addInterceptor(Interceptor interceptor) {
    interceptorList.add(interceptor);
    interceptors = interceptorList.iterator();
  }
}

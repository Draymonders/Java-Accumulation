package demo.pattern.interceptor;

import demo.pattern.interceptor.impl.AuditInterceptor;
import demo.pattern.interceptor.impl.LogInterceptor;

/**
 * @Description: 拦截器模式 demo
 * @Date 2020/10/04 02:07
 * @auther Draymonder
 */
public class Main {

  public static void main(String[] args) {
    TargetInvocation targetInvocation = new TargetInvocation();

    targetInvocation.addInterceptor(new LogInterceptor());
    targetInvocation.addInterceptor(new AuditInterceptor());

    targetInvocation.setInput("input");
    targetInvocation.setTarget((input) -> "output");
    targetInvocation.invoke();
  }
}

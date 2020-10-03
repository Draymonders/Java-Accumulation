package demo.pattern.interceptor.impl;

import demo.pattern.interceptor.Interceptor;
import demo.pattern.interceptor.TargetInvocation;

/**
 * @Description: 日志拦截器
 * @Date 2020/10/04 02:06
 * @auther Draymonder
 */
public class LogInterceptor implements Interceptor {

  @Override
  public String intercept(TargetInvocation targetInvocation) {
    System.out.println("Logging begin");
    String output = targetInvocation.invoke();
    System.out.println("Logging end");
    return output;
  }
}

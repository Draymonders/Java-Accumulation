package demo.pattern.interceptor.impl;

import demo.pattern.interceptor.Interceptor;
import demo.pattern.interceptor.TargetInvocation;

/**
 * @Description:
 * @Date 2020/10/04 02:02
 * @auther Draymonder
 */
public class AuditInterceptor implements Interceptor {

  @Override
  public String intercept(TargetInvocation targetInvocation) {
    if (targetInvocation.getTarget() == null) {
      throw new IllegalArgumentException("Target is null");
    }
    System.out.println("Audit successed");
    return targetInvocation.invoke();
  }
}

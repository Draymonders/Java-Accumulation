package demo.pattern.interceptor;

public interface Interceptor {
  String intercept(TargetInvocation targetInvocation);
}

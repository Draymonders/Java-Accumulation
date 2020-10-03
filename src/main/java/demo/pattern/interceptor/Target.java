package demo.pattern.interceptor;

public interface Target {

  /**
   * 这里mock的是发送请求, 获取结果
   */
  String execute(String input);
}

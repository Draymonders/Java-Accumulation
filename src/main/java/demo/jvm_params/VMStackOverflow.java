package demo.jvm_params;

import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;

/**
 * @Description: 模拟栈溢出
 * @Date 2020/08/23 20:29
 * @auther Draymonder
 */
@Slf4j
public class VMStackOverflow {

  public static int stackLen = 0;

  public static void stackLeak() {
    stackLen ++;
    stackLeak();
  }

  /**
   * VM-params:
   * -Xss512k
   *
   * output: [INFO ] 2020-08-23 20:37:37 method:demo.jvm_params.VMStackOverflow.main(VMStackOverflow.java:31) stack len: 8704
   */
  public static void main(String[] args) {
    try {
      stackLeak();
    } catch (Throwable e) {
      log.info("stack len: {}", stackLen);
      throw e;
    }

  }
}

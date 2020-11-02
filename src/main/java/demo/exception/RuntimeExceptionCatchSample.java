package demo.exception;

import java.util.List;
import lombok.extern.slf4j.Slf4j;

/**
 * runtime异常的catch
 */
@Slf4j
public class RuntimeExceptionCatchSample {

  public static void main(String[] args) {
    List<String> lists = List.of("1", "2", "3", "4");
    try {
      runtimeException();
    } catch (Exception e) {
      log.error("str: {}\nlist: {}", "2333", lists, e);
    }
  }

  static void runtimeException() {
    throw new RuntimeException("just new a runtime exception");
  }

}

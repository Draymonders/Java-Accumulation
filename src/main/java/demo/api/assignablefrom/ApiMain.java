package demo.api.assignablefrom;

import lombok.extern.slf4j.Slf4j;

/**
 * @Description: Api Main
 * @Date 2020/08/22 15:02
 * @auther Draymonder
 */
@Slf4j
public class ApiMain {

  public static void main(String[] args) {
    log.info("A isAssignable From A : {}", A.class.isAssignableFrom(A.class));
    log.info("B isAssignable From A : {}", B.class.isAssignableFrom(A.class));
    log.info("A isAssignable From C : {}", A.class.isAssignableFrom(C.class));
    log.info("B isAssignable From C : {}", B.class.isAssignableFrom(C.class));
    log.info("C isAssignable From C : {}", C.class.isAssignableFrom(C.class));
  }

}

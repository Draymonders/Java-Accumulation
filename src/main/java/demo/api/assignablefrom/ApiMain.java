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
    log.info("A isAssignable From A : {}", AA.class.isAssignableFrom(AA.class));
    log.info("B isAssignable From A : {}", BB.class.isAssignableFrom(AA.class));
    log.info("A isAssignable From C : {}", AA.class.isAssignableFrom(CC.class));
    log.info("B isAssignable From C : {}", BB.class.isAssignableFrom(CC.class));
    log.info("C isAssignable From C : {}", CC.class.isAssignableFrom(CC.class));
  }

}

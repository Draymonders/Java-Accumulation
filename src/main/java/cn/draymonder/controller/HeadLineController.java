package cn.draymonder.controller;

import cn.draymonder.entity.bo.HeadLine;
import cn.draymonder.entity.dto.Result;
import cn.draymonder.service.solo.HeadLineService;
import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.simpleframework.core.annotation.Controller;
import org.simpleframework.inject.annotation.Autowired;

/**
 * @Description: 头条Controller
 * @Date 2020/08/18 23:22
 * @auther Draymonder
 */
@Controller
@Slf4j
public class HeadLineController {

  @Autowired
  private HeadLineService headLineService;

  public Result<Boolean> addHeadLine(HttpServletRequest req, HttpServletResponse resp) {
    Result<Boolean> booleanResult = headLineService.addHeadLine(new HeadLine());
    try {
      resp.getWriter().println("add head line success...");
    } catch (IOException e) {
      log.error("response output error, errmsg: {}", e.getMessage());
      // e.printStackTrace();
    }

    log.info("add head line success...");
    return booleanResult;
  }

  public Result<List<HeadLine>> getHeadLine(HttpServletRequest req, HttpServletResponse resp) {
    return headLineService.queryHeadLine(new HeadLine(), 0, 3);
  }
}

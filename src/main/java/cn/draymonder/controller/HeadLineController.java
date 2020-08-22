package cn.draymonder.controller;

import cn.draymonder.entity.bo.HeadLine;
import cn.draymonder.entity.dto.Result;
import cn.draymonder.service.solo.HeadLineService;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.simpleframework.core.annotation.Controller;
import org.simpleframework.inject.annotation.Autowired;

/**
 * @Description: 头条Controller
 * @Date 2020/08/18 23:22
 * @auther Draymonder
 */
@Controller
public class HeadLineController {

  @Autowired
  private HeadLineService headLineService;

  public Result<Boolean> addHeadLine(HttpServletRequest req, HttpServletResponse resp) {
    return headLineService.addHeadLine(new HeadLine());
  }

  public Result<List<HeadLine>> getHeadLine(HttpServletRequest req, HttpServletResponse resp) {
    return headLineService.queryHeadLine(new HeadLine(), 0, 3);
  }
}

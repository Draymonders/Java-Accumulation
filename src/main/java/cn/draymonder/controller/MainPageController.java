package cn.draymonder.controller;

import cn.draymonder.entity.dto.MainPageInfoDTO;
import cn.draymonder.entity.dto.Result;
import cn.draymonder.service.combine.MainPageInfoService;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.simpleframework.core.annotation.Controller;
import org.simpleframework.inject.annotation.Autowired;

/**
 * @Description: 首页Controller
 * @Date 2020/08/18 23:20
 * @auther Draymonder
 */
@Controller
@Getter
@Slf4j
public class MainPageController {

  @Getter
  @Autowired(value = "MainPageInfoServiceImpl")
  private MainPageInfoService mainPageInfoService;

  public Result<MainPageInfoDTO> getMainPage(HttpServletRequest req, HttpServletResponse resp) {
    Result<MainPageInfoDTO> mainPageInfo = mainPageInfoService.getMainPageInfo(0, 10);
    log.info("get main page success");
    try {
      resp.getWriter().println("get main page success");
    } catch (IOException e) {
      log.error("response output error, errmsg: {}", e.getMessage());
//      e.printStackTrace();
    }
    return mainPageInfo;
  }
}

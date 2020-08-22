package cn.draymonder.controller;

import cn.draymonder.entity.dto.MainPageInfoDTO;
import cn.draymonder.entity.dto.Result;
import cn.draymonder.service.combine.MainPageInfoService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.Getter;
import org.simpleframework.core.annotation.Controller;
import org.simpleframework.inject.annotation.Autowired;

/**
 * @Description: 首页Controller
 * @Date 2020/08/18 23:20
 * @auther Draymonder
 */
@Controller
@Getter
public class MainPageController {

  @Getter
  @Autowired
  private MainPageInfoService mainPageInfoService;

  public Result<MainPageInfoDTO> getMainPage(HttpServletRequest req, HttpServletResponse resp) {
    Result<MainPageInfoDTO> mainPageInfo = mainPageInfoService.getMainPageInfo(0, 10);
    return mainPageInfo;
  }
}

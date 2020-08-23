package cn.draymonder.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.simpleframework.core.annotation.Componenet;
import org.simpleframework.inject.DependencyInjector;
import org.simpleframework.inject.annotation.Autowired;

/**
 * @Description: 分发servlet, 目的是为了尽可能减少servlet
 * @Date 2020/08/18 23:18
 * @auther Draymonder
 */
@Slf4j
@WebServlet("/")
public class DispatcherServlet extends HttpServlet {

  DependencyInjector dependencyInjector;

  private MainPageController mainPageController;

  private HeadLineController headLineController;

  @Override
  public void init() throws ServletException {
    String basePackageName = "cn.draymonder";
    dependencyInjector = new DependencyInjector(basePackageName);
    dependencyInjector.doIoC();
    mainPageController = (MainPageController)dependencyInjector.getBean(MainPageController.class);
    headLineController = (HeadLineController)dependencyInjector.getBean(HeadLineController.class);
  }

  @Override
  protected void service(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    String reqPath = req.getServletPath();
    String method = req.getMethod();

    if ("/mainpage".equals(reqPath) && "GET".equals(method)) {
      mainPageController.getMainPage(req, resp);
    }
    if ("/headline".equals(reqPath) && "POST".equals(method)) {
      headLineController.addHeadLine(req, resp);
    }
  }
}

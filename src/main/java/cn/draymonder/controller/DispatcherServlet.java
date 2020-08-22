package cn.draymonder.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description: 分发servlet, 目的是为了尽可能减少servlet
 * @Date 2020/08/18 23:18
 * @auther Draymonder
 */
public class DispatcherServlet extends HttpServlet {

  @Override
  protected void service(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    String reqPath = req.getServletPath();
    String method = req.getMethod();

    if ("/mainpage".equals(reqPath) && "GET".equals(method)) {
      new MainPageController().getMainPage(req, resp);
    }
    if ("/headline".equals(reqPath) && "POST".equals(method)) {
      new HeadLineController().addHeadLine(req, resp);
    }
  }
}

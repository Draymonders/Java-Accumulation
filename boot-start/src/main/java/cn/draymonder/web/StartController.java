package cn.draymonder.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Description: start controller (first sample)
 * @Date 2020/11/07 16:30
 * @auther Draymonder
 */
@Controller
public class StartController {

  @Autowired
  private Environment environment;

  @GetMapping("/lover")
  @ResponseBody
  public String getLover() {
    return environment.getProperty("lover");
  }

  @GetMapping("/login")
  @ResponseBody
  public String login(@RequestParam("username") String username,
      @RequestParam("password") String password) {
    return String.format("%s %s", username, password);
  }
}

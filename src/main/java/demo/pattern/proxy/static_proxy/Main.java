package demo.pattern.proxy.static_proxy;

import demo.pattern.proxy.UserDaoImpl;

public class Main {

  public static void main(String[] args) {
    UserDaoImpl userDao = new UserDaoImpl();
    UserDaoProxy userDaoProxy = new UserDaoProxy(userDao);
    userDaoProxy.save();
  }
}

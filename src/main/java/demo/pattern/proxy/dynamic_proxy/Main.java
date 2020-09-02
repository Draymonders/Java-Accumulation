package demo.pattern.proxy.dynamic_proxy;

import demo.pattern.proxy.UserDao;
import demo.pattern.proxy.UserDaoImpl;

public class Main {

  public static void main(String[] args) {
    UserDao userDao = new UserDaoImpl();
    System.out.println(userDao.getClass());

    UserDao userDaoProxy = (UserDao) new UserDaoProxy(userDao).getProxyInstance();
    System.out.println(userDaoProxy.getClass());
    userDaoProxy.save();
  }
}

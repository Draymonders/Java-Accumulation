package demo.pattern.proxy.dynamic_proxy;

import demo.pattern.proxy.UserDao;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;


public class UserDaoProxy implements InvocationHandler {

  UserDao userDao;

  UserDaoProxy(UserDao userDao) {
    this.userDao = userDao;
  }


  @Override
  public Object invoke(Object o, Method method, Object[] args) throws Throwable {
    System.out.println("开启事务");
    Object ret = method.invoke(userDao, args);
    System.out.println("提交事务");
    return ret;
  }

  public Object getProxyInstance() {
    System.out.println("get userDao proxy");
    return Proxy
        .newProxyInstance(this.getClass().getClassLoader(), userDao.getClass().getInterfaces(),
            this);
  }
}

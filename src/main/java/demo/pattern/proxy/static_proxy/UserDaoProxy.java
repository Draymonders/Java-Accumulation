package demo.pattern.proxy.static_proxy;

import demo.pattern.proxy.UserDao;

/**
 * @Description:
 * @Date 2020/09/02 22:22
 * @auther Draymonder
 */
public class UserDaoProxy implements UserDao {

  private UserDao userDao;

  UserDaoProxy(UserDao userDao) {
    this.userDao = userDao;
  }

  @Override
  public void save() {
    System.out.println("开启事务");
    this.userDao.save();
    System.out.println("提交事务");
  }
}

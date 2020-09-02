package demo.pattern.proxy.static_proxy;

public class UserDaoImpl implements UserDao {

  @Override
  public void save() {
    System.out.println("save user");
  }
}

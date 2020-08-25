# ioc-implement

IOC容器简易实现
## How to Run
1. `git clone && cd ./ioc-implement`
2. `mvn clean install -DTest.skip=true` 打包
3. `mvn tomcat7:run` 使用tomcat7作为servlet容器
4. `curl -s http://localhost:8080/ioc-implement/mainpage` 看是否得到`get main page success`, 如果得到, 则搭建成功，如若失败，请移至issue区
 
## table
```
- demo
  - anonation      // 注解相关操作
  - api            // api 相关学习
  - pattern        // 设计模式
  - reflect        // 反射
  - jvm_params     // JVM相关参数学习
- org.simpleframework
  - core           // bean 相关实现
  - util           // 封装的工具类
  - inject         // 依赖注入, 使用 @Autowired 来注入
- cn.draymonder    // 业务CURD Demo, 使用Servlet作为容器      
```
## reference
- [Maven 使用 tomcat7-maven-plugin](https://www.cnblogs.com/dyh004/p/11578954.html)
- [Test Order Method](https://www.baeldung.com/junit-5-test-order) 
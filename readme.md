# Java-accumulation
- 收集Java相关的自己实践的各种demo
    - 包括不限于, Java基础实践, Java集合源码分析, 设计模式, 中间件使用, 工具类, api尝试, Spring简易框架实现

## table
```
- demo
  - anonation      // 注解相关操作
  - api            // api 相关学习
  - pattern        // 设计模式
  - reflect        // 反射
  - jvm_params     // JVM相关参数学习
  - collection     // Java 集合复习巩固
- org.simpleframework
  - core           // bean 相关实现
  - util           // 封装的工具类
  - inject         // 依赖注入, 使用 @Autowired 来注入
- cn.draymonder    // 业务CURD Demo, 使用Servlet作为容器      
```

## IoC-Implements
### How to Run
1. `git clone && cd ./ioc-implement`
2. `mvn clean install -DskipTests=true` 打包
3. `mvn tomcat7:run` 使用tomcat7作为servlet容器
4. `curl -s http://localhost:8080/ioc-implement/mainpage` 看是否得到`get main page success`, 如果得到, 则搭建成功，如若失败，请移至issue区
 

## reference
- [Maven 使用 tomcat7-maven-plugin](https://www.cnblogs.com/dyh004/p/11578954.html)
- [Test Order Method](https://www.baeldung.com/junit-5-test-order) 
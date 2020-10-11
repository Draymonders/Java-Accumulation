package demo.api.file;

/**
 * 函数式声明接口, 需要加上@FunctionalInterface
 */
@FunctionalInterface
public interface FileConsumer {

  void consumeContent(String content);

}
package demo.api.file;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @Description: 文件服务
 * @Date 2020/10/11 17:25
 * @auther Draymonder
 */
public class FileService {

  public void consumeFileContent(String url, FileConsumer fileConsumer)
      throws IOException {
    File file = new File(url);
    BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file));

    StringBuilder sb = new StringBuilder();
    byte[] buffer = new byte[1024];
    int readAvaiable = -1;
    while ((readAvaiable = bufferedInputStream.read(buffer, 0, 1024)) != -1) {
      String line = new String(buffer, 0, readAvaiable);
      sb.append(line + "\n");
    }
    String content = sb.toString();
    fileConsumer.consumeContent(content);
  }

  public static void main(String[] args) throws IOException {
    FileService fileService = new FileService();
    fileService.consumeFileContent(
        "/home/draymonder/Desktop/practice/Java-Accumulation/src/main/java/demo/api/file/FileConsumer.java",
        System.out::println);
  }
}
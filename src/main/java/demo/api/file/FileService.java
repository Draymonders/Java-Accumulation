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

  public void newConsumeFileContent(String url, FileConsumer fileConsumer) {
    File file = new File(url);
    try (
        FileInputStream fileInputStream = new FileInputStream(file);
        BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
    ) {
      StringBuilder sb = new StringBuilder();
      byte[] buffer = new byte[1024];
      int readAvaiable = -1;
      while ((readAvaiable = bufferedInputStream.read(buffer, 0, 1024)) != -1) {
        String line = new String(buffer, 0, readAvaiable);
        sb.append(line + "\n");
      }
      String content = sb.toString();
      fileConsumer.consumeContent(content);
    } catch (IOException e) {
      System.out.println("error");
    }
  }

  public void oldConsumeFileContent(String url, FileConsumer fileConsumer) {
    File file = new File(url);
    FileInputStream fileInputStream = null;

    BufferedInputStream bufferedInputStream = null;
    try {
      fileInputStream = new FileInputStream(file);
      bufferedInputStream = new BufferedInputStream(fileInputStream);

      StringBuilder sb = new StringBuilder();
      byte[] buffer = new byte[1024];
      int readAvaiable = -1;
      while ((readAvaiable = bufferedInputStream.read(buffer, 0, 1024)) != -1) {
        String line = new String(buffer, 0, readAvaiable);
        sb.append(line + "\n");
      }
      String content = sb.toString();
      fileConsumer.consumeContent(content);
    } catch (IOException e) {
      System.out.println("error");
    } finally {
      if (bufferedInputStream != null) {
        try {
          bufferedInputStream.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
      if (fileInputStream != null) {
        try {
          fileInputStream.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
  }

  public static void main(String[] args) {
    FileService fileService = new FileService();
    fileService.oldConsumeFileContent(
        "/home/draymonder/Desktop/practice/Java-Accumulation/src/main/java/demo/api/file/FileConsumer.java",
        System.out::println);

    System.out.println("====");

    fileService.newConsumeFileContent(
        "/home/draymonder/Desktop/practice/Java-Accumulation/src/main/java/demo/api/file/FileConsumer.java",
        System.out::println);
  }
}
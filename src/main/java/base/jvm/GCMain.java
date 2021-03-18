package base.jvm;


/**
 * gc测试  -XX:PrintGC
 */
public class GCMain {
  static int sixMb = 6 * 1024 * 1024;

  void localVarGc1() {
    byte[] a = new byte[sixMb];
    System.gc();
  }

  void localVarGc2() {
    byte[] a = new byte[sixMb];
    a = null;
    System.gc();
  }

  void localVarGc3() {
    {
      byte[] a = new byte[sixMb];
    }
    System.gc();
  }

  void localVarGc4() {
    {
      byte[] a = new byte[sixMb];
    }
    int c = 10;
    System.gc();
  }

  void localValGc5() {
    localVarGc1();
    System.gc();
  }

  public static void main(String[] args) {

  }

}

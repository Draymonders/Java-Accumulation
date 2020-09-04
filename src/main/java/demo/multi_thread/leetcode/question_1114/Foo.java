package demo.multi_thread.leetcode.question_1114;

class Foo {

  public Foo() {

  }
  volatile int state = 0;

  public void first(Runnable printFirst) throws InterruptedException {
    // printFirst.run() outputs "first". Do not change or remove this line.
    printFirst.run();
    synchronized (this) {
      state = 1;
    }
  }

  public void second(Runnable printSecond) throws InterruptedException {
    // printSecond.run() outputs "second". Do not change or remove this line.
    while (state != 1);
    synchronized (this) {
      if (state == 1) {
        printSecond.run();
        state = 2;
      }
    }
  }

  public void third(Runnable printThird) throws InterruptedException {
    // printThird.run() outputs "third". Do not change or remove this line.
    while (state != 2);
    synchronized (this) {
      if (state == 2) {
        printThird.run();
        state = 3;
      }
    }
  }
}
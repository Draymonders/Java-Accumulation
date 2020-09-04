package demo.multi_thread.leetcode.question_1115;

import java.util.concurrent.Semaphore;

class FooBar {

  private int n;

  public FooBar(int n) {
    this.n = n;
  }

  private volatile Semaphore fooLock = new Semaphore(1);
  private volatile Semaphore barLock = new Semaphore(0);

  public void foo(Runnable printFoo) throws InterruptedException {
    // printFoo.run() outputs "foo". Do not change or remove this line.
    for (int i = 0; i < n; i++) {
      fooLock.acquire();
      printFoo.run();
      barLock.release();
    }
  }

  public void bar(Runnable printBar) throws InterruptedException {
    // printBar.run() outputs "bar". Do not change or remove this line.
    for (int i = 0; i < n; i++) {
      barLock.acquire();
      printBar.run();
      fooLock.release();
    }
  }
}
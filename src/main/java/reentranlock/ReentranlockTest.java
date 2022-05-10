package reentranlock;

import java.util.concurrent.locks.ReentrantLock;

public class ReentranlockTest {
    private static ReentrantLock reentrantLock = new ReentrantLock(true);
    public static void main(String[] args) {
        for (int i=0;i<5;i++) {
            Thread thread1 = new Thread() {
                @Override
                public void run() {
                    System.out.println("我来了：" + Thread.currentThread().getName());
                    startServiceProcess(Thread.currentThread());
                }
            };
            thread1.start();
        }
    }

    public static void startServiceProcess(Thread tread) {
        reentrantLock.lock();
        System.out.println(tread.getName()+" 获得锁成功");
        reentrantLock.unlock();
    }
}

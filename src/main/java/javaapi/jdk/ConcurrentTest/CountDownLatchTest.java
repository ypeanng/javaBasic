package javaapi.jdk.ConcurrentTest;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.junit.Test;

public class CountDownLatchTest {

    // 10 个人都去摘苹果
    @Test
    public void test() throws InterruptedException {
        ExecutorService executor = Executors.newCachedThreadPool();
        System.out.println("start 1");
        final CountDownLatch countDownLatch = new CountDownLatch(10);
        for (int i = 0; i < 10; i++) {
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName());
                    countDownLatch.countDown();
                }
            });
        }
        countDownLatch.await();
        System.out.println("complete");
    }

    // 10 个人都去摘苹果
    @Test
    public void testPool() throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(20);
        System.out.println("start");
        final CountDownLatch countDownLatch = new CountDownLatch(20);
        for (int i = 0; i < 20; i++) {
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName());
                    countDownLatch.countDown();
                }
            });
        }
        countDownLatch.await();
        System.out.println("complete");
    }

}

package cn.guxiangfly.distributelock.springbootstudy.config;


import cn.guxiangfly.distributelock.springbootstudy.lock.v1.MLock;
import org.junit.jupiter.api.Test;

import java.util.concurrent.locks.Lock;

/**
 * @author guxiang (guxiang@rd.netease.com)
 * @date 2019/11/10 17:37
 */

public class MLockTest {

    public static int m = 0;
    public static Lock lock = new MLock();

    @Test
    public void testLock() throws InterruptedException {
        Thread[] threads = new Thread[100];

        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                try {
                    lock.lock();
                    for (int j = 0; j < 100; j++) {
                        m++;
                    }
                } finally {
                    lock.unlock();
                }
            });
        }

        for (Thread thread : threads) {
            thread.start();
        }
        for (Thread thread : threads) {
            thread.join();
        }
        System.out.println(m);
    }

}

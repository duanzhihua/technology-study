package cn.guxiangfly.condition;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author guxiang (guxiang@rd.netease.com)
 * @date 2019/11/2 20:17
 */
public class ConstantsTest2 {
    static {
        System.out.println("ConstantsTest2 初始化了....");
    }

    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        ExecutorService exec2 = Executors.newFixedThreadPool(1);
        String aa = ConstantsTest.AA;
        new ReentrantLock();
        new ConcurrentHashMap<>();
        new HashMap<>();
    }
}

package cn.guxiangfly.distributelock.springbootstudy.lock.v1;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @author guxiang (guxiang@rd.netease.com)
 * @date 2019/11/10 17:29
 */
public class MLock implements Lock {

    private volatile int i = 0;

    @Override
    public void lock() {
        synchronized (this){
            if (i!=0){
                try {
                    this.wait();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            i=1;
        }
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public void unlock() {
        synchronized (this){
            i=0;
            this.notifyAll();
        }
    }

    @Override
    public Condition newCondition() {
        return null;
    }
}

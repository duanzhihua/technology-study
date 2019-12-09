package cn.guxiangfly.distributelock.springbootstudy.lock.v2;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @author guxiang (guxiang@rd.netease.com)
 * @date 2019/11/10 17:29
 */
public class MLock implements Lock {

    private volatile int i = 0;

    private Sync sync = new Sync();

    @Override
    public void lock() {
        sync.acquire(1);
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
      sync.release(1);
    }

    @Override
    public Condition newCondition() {
        return null;
    }

    /**
     * AQS 内部主要有两个东西 一个是一个类似atomicInteger的state（注：aqs内部为了将来的优化，不是直接使用的atomicInteger）
     *           一个是 CLH （三个发明这个队列的科学家的名字）等待队列，
     */
    public class Sync extends AbstractQueuedSynchronizer {

        @Override
        public boolean tryAcquire(int arg) {
            if (arg!=1){
                throw new RuntimeException("arg not is 1");
            }
            if (compareAndSetState(0, 1)) {
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }
            return false;
        }

        @Override
        protected boolean tryRelease(int arg) {
            assert arg==1;
            if (!isHeldExclusively()) {
                throw new IllegalMonitorStateException();
            }
            setExclusiveOwnerThread(null);
            setState(0);
            return super.tryRelease(arg);
        }

        @Override
        protected boolean isHeldExclusively() {

            return getExclusiveOwnerThread() == Thread.currentThread();
        }
    }
}

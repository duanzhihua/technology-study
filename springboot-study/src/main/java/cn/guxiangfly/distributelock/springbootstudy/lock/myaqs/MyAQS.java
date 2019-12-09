package cn.guxiangfly.distributelock.springbootstudy.lock.myaqs;


import sun.misc.Unsafe;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.locks.LockSupport;

/**
 * @author guxiang (guxiang@rd.netease.com)
 * @date 2019/11/19 21:26
 */
public class MyAQS {
    private volatile  int state = 0;

    private final static Unsafe unsafe = Unsafe.getUnsafe();

    private  static long stateOffset;

    private Thread lockHolder;

    private ConcurrentLinkedQueue<Thread> queue = new ConcurrentLinkedQueue<>();


    static {
        try {
            stateOffset = unsafe.objectFieldOffset(MyAQS.class.getDeclaredField("state"));
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    public boolean compareAndSwapState(int expect ,int update){
        return unsafe.compareAndSwapInt(this,stateOffset,expect,update);
    }


    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    /**
     * 获取锁
     * T1 T2 T3三个线程过来
     * T1抢占到锁  T2 T3 没有抢占到
     */
    public void lockAndDoSomething(){
        Thread current = Thread.currentThread();
        //此处进行加锁
        while (true){
            int state = getState();
            if (compareAndSwapState(0,1)){
                lockHolder = current;
                break;
            }
            // T2 T3 进入阻塞状态
            LockSupport.park();
        }


        System.out.println("做业务");


        // T1需要解锁
        while (true){
            int state = getState();
            if (state!=0 && lockHolder==current){
                compareAndSwapState(state,0);
                break;
            }
            //释放锁需要唤醒被阻塞线程: 这个需要有一个先入先出的队列来获取锁
            LockSupport.unpark(queue.poll());
        }

        System.out.println("处理成功");
    }


}

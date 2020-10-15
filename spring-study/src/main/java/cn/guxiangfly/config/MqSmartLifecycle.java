package cn.guxiangfly.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.SmartLifecycle;

import java.util.concurrent.atomic.AtomicLong;

@Slf4j
public abstract class MqSmartLifecycle   implements SmartLifecycle {

    private static final String LOG_PREFIX = "[MqSmartLifecycle]- ";


    /**
     * 执行中的个数
     */
    AtomicLong runningCount = new AtomicLong(0);

    @Override
    public boolean isAutoStartup() {
        return true;
    }

    /**
     * 在 spring 启动关闭使用
     * 单线程
     */
    boolean running = false;


    /**
     * 执行
     *
     * @param callback
     */
    @Override
    public void stop(Runnable callback) {
        log.info("{} callback_stop {}", LOG_PREFIX, this.getClass().getSimpleName());
        running = false;
        try {
            doStop();
        } catch (Exception e) {
            log.error(LOG_PREFIX + "doStop", e);
        }
        Integer loopCount = getLoopCount();
        for (Integer i = 0; i < loopCount; i++) {
            long currentCount = this.runningCount.get();
            log.warn("{},currentCount:{}", LOG_PREFIX, currentCount);
            if (currentCount < 1) {
                break;
            }
            try {
                Thread.sleep(5L * i);
            } catch (InterruptedException e) {
                log.error(LOG_PREFIX + "Thread.sleep", e);
            }
        }
        callback.run();
    }



    @Override
    public void start() {
        log.info("{} start {}", LOG_PREFIX, this.getClass().getSimpleName());
        running = true;
        try {
            doStart();
        } catch (Exception e) {
            log.error(LOG_PREFIX + "doStop", e);
        }
    }




    @Override
    public void stop() {
        log.info("{} stop {}", LOG_PREFIX, this.getClass().getSimpleName());
    }


    @Override
    public boolean isRunning() {
        log.info("{} isRunning {},running:{}", LOG_PREFIX, this.getClass().getSimpleName(), running);
        return running;
    }


    /**
     * 值越大，优先执行
     *
     * @return
     */
    @Override
    public int getPhase() {
        return Integer.MAX_VALUE;
    }

    public MqSmartLifecycle() {
    }





    public AtomicLong getRunningCount() {
        return runningCount;
    }

    /**
     * 启动的契机
     */
    protected abstract void doStart();

    /**
     * 关闭的契机
     */
    protected abstract void doStop();

    /**
     * 获取循环次数
     *
     * @return
     */
    public abstract Integer getLoopCount();
}

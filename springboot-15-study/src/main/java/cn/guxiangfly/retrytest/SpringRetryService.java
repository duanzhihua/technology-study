package cn.guxiangfly.retrytest;

import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

/**
 * @Author guxiang02
 * @Date 2020/11/24
 **/
@Service
public class SpringRetryService {

    @Retryable(value = {RetryFinalReturnNullException.class}, maxAttempts = 1000)
    public Object retryTest() throws Exception {
        System.out.println(Thread.currentThread().getName());
        System.out.println("retrying...");
        throw new RetryFinalReturnNullException("RemoteAccessException....");
    }

    @Recover
    public Object recover(RetryFinalReturnNullException e) {
        System.out.println(e.getMessage());
        System.out.println("executing recover....");
        return "null";
    }

    public Object retryTest2() throws Exception {
        Object o = retryTest();
        return o;
    }
}

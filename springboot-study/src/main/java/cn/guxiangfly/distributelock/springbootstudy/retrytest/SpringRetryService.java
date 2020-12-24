package cn.guxiangfly.distributelock.springbootstudy.retrytest;

import org.springframework.remoting.RemoteAccessException;
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

    @Retryable(value= {RemoteAccessException.class},maxAttempts = 5,backoff = @Backoff(delay = 5000L,multiplier = 1))
    public Object retryTest() throws Exception {
        System.out.println("retrying...");
        throw new RetryReturnNullException("RemoteAccessException....");
    }
    @Recover
    public Object recover(RetryReturnNullException e) {
        System.out.println(e.getMessage());
        System.out.println("executing recover....");
        return "null";
    }
}

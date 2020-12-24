package cn.guxiangfly.distributelock.springbootstudy.retrytest;

/**
 * @Author guxiang02
 * @Date 2020/11/24
 **/
public class RetryThrowOutException extends RuntimeException {

    public RetryThrowOutException(String message){
        super(message);
    }
}

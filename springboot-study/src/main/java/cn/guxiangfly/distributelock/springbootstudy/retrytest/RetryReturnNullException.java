package cn.guxiangfly.distributelock.springbootstudy.retrytest;

/**
 * @Author guxiang02
 * @Date 2020/11/24
 **/
public class RetryReturnNullException extends RuntimeException {

    public RetryReturnNullException(String message){
        super(message);
    }
}

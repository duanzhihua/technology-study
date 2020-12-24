package cn.guxiangfly.retrytest;

/**
 * @Author guxiang02
 * @Date 2020/11/24
 **/
public class RetryFinalThrowOutException extends RuntimeException {

    public RetryFinalThrowOutException(String message){
        super(message);
    }
}

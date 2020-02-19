package cn.guxiangfly.distributelock.springbootstudy.zookeeper;

/**
 * @Author guxiang02
 * @Date 2020/1/8 14:34
 **/
public class Lock {
    String lockId;
    String path;
    boolean active;
}
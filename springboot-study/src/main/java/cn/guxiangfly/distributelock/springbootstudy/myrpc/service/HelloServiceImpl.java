package cn.guxiangfly.distributelock.springbootstudy.myrpc.service;

import cn.guxiangfly.distributelock.springbootstudy.myrpc.bean.User;

/**
 * @author guxiang (guxiang@rd.netease.com)
 * @date 2019/11/12 21:05
 */
public class HelloServiceImpl implements IHelloService {
    @Override
    public String sayHello(String context) {
        return "hello world :"+context;
    }

    @Override
    public String saveUser(User user) {
        System.out.println("user >"+user);
        return "success";
    }
}

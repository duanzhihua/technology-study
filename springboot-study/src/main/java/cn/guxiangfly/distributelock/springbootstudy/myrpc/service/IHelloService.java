package cn.guxiangfly.distributelock.springbootstudy.myrpc.service;

import cn.guxiangfly.distributelock.springbootstudy.myrpc.bean.User;

/**
 * @author guxiang (guxiang@rd.netease.com)
 * @date 2019/11/12 21:03
 */
public interface IHelloService {

    String sayHello(String context);

    String saveUser(User user);
}

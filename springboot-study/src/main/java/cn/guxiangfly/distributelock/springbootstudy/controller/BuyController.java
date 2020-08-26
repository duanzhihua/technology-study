package cn.guxiangfly.distributelock.springbootstudy.controller;

import cn.guxiangfly.distributelock.springbootstudy.config.ZookeeperConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.concurrent.locks.Lock;

/**
 * @author guxiang (guxiang@rd.netease.com)
 * @date 2019/11/3 14:59
 */
@Controller
public class BuyController {

    @Autowired
    private ZookeeperConfig zookeeperConfig;



    @GetMapping("/buy")
    public String getBuy(){
//        zookeeperConfig.getLock();
//
//        zookeeperConfig.releaseLock();
        return "ok";
    }
}

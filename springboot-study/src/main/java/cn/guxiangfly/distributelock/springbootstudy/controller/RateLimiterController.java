package cn.guxiangfly.distributelock.springbootstudy.controller;

import com.google.common.util.concurrent.RateLimiter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Author guxiang02
 * @Date 2020/8/7
 **/

public class RateLimiterController {
    public static RateLimiter  rateLimiter = RateLimiter.create(2);


    public static void main(String[] args) {

        for (int i = 0; i < 20; i++) {
            System.out.println(rateLimiter.acquire());
        }
    }
}

package cn.guxiangfly.distributelock.springbootstudy.retrytest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author guxiang02
 * @Date 2020/11/24
 **/
@RestController
public class RetryController {


    @Autowired
    SpringRetryService springRetryService;

    @GetMapping("/retrytest")
    public Object retrytest() throws Exception {
        return springRetryService.retryTest();
    }
}

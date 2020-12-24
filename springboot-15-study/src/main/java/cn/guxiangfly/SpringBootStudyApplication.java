package cn.guxiangfly;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.retry.annotation.EnableRetry;

/**
 * @Author guxiang02
 * @Date 2020/7/9
 **/
@SpringBootApplication
@EnableRetry
@EnableAspectJAutoProxy(proxyTargetClass=true)
public class SpringBootStudyApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootStudyApplication.class, args);
    }
}

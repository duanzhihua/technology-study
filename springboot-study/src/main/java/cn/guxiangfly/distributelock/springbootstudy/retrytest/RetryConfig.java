package cn.guxiangfly.distributelock.springbootstudy.retrytest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.annotation.EnableRetry;

/**
 * @Author guxiang02
 * @Date 2020/11/24
 **/
@Configuration
@EnableRetry
public class RetryConfig {

    @Bean
    public SpringRetryService service() {
        return new SpringRetryService();
    }
}

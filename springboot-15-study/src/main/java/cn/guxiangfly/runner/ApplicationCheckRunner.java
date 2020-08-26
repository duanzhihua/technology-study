package cn.guxiangfly.runner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * @Author guxiang02
 * @Date 2020/7/9
 **/
@Component
public class ApplicationCheckRunner implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments args) throws Exception {
        throw new RuntimeException("关闭应用");
    }
}

package cn.guxiangfly.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @Author guxiang02
 * @Date 2019/12/18 21:38
 **/
@Configuration
@ComponentScan({"cn.guxiangfly.dao","cn.guxiangfly.service","cn.guxiangfly.controller","cn.guxiangfly.event"})
public class MainConfigAutowired {
}

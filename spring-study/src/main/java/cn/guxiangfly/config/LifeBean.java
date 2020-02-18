package cn.guxiangfly.config;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @Author guxiang02
 * @Date 2020/1/29 09:36
 **/
@Component
public class LifeBean {
    public LifeBean(){
        System.out.println("对象创建了");
    }

    @PostConstruct
    public void init(){
        System.out.println("对象初始化了");
    }

    /**
     * 单例方法的SpringBean对象 生命周期与容器相同， 容器创建的时候对象创建， 容器销毁的时候对象销毁
     */
    @PreDestroy
    public void LifeBean(){
        System.out.println("对象销毁了");
    }

}

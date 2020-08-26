package cn.guxiangfly.event;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @Author guxiang02
 * @Date 2020/5/21
 **/
@Component
public class MyEventListener implements ApplicationListener {

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        System.out.println("MyEventListener:" +Thread.currentThread().getName());
        System.out.println(event.toString());

    }
}

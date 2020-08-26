package cn.guxiangfly.event;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Service;

/**
 * @Author guxiang02
 * @Date 2020/5/21
 **/
@Service
public class Circle implements ApplicationEventPublisherAware {

    private ApplicationEventPublisher applicationEventPublisher;


    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }


    public void  draw (){
        DrawEvent drawEvent = new DrawEvent(this);
        applicationEventPublisher.publishEvent(drawEvent);
        applicationEventPublisher.publishEvent(drawEvent);
    }
}

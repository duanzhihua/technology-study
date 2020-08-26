package cn.guxiangfly.test1;

import cn.guxiangfly.config.MainConfigAutowired;
import cn.guxiangfly.config.TestConfig;
import cn.guxiangfly.event.Circle;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Author guxiang02
 * @Date 2020/5/21
 **/
public class SpringEventTest {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(MainConfigAutowired.class);
        System.out.println("SpringEventTest:" +Thread.currentThread().getName());
        Circle bean = annotationConfigApplicationContext.getBean(Circle.class);
        bean.draw();
    }
}

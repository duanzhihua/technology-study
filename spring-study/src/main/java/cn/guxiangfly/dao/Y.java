package cn.guxiangfly.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @Author guxiang02
 * @Date 2020/3/1
 **/
@Component
public class Y {

/*    @Autowired
    X x;*/

    public Y(){
        System.out.println("create Y");
    }


    @PostConstruct
    public void postConstructInit(){
        System.out.println("y postConstructInit");
    }
}

package cn.guxiangfly.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author guxiang02
 * @Date 2020/3/1
 **/
@Component
public class X {

    @Autowired
    Y y;

    public X(){
        System.out.println("create X");
    }

}

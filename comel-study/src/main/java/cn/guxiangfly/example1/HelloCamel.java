package cn.guxiangfly.example1;

import cn.guxiangfly.example1.HelloWorldRoute;
import org.apache.camel.impl.DefaultCamelContext;

/**
 * @Author guxiang02
 * @Date 2019/12/30 16:18
 **/
public class HelloCamel {
    public static void main(String[] args) throws Exception{
        DefaultCamelContext camelContext = new DefaultCamelContext();


        // 将我们编排的一个完整消息路由过程，加入到上下文中
        camelContext.addRoutes(new HelloWorldRoute());


        camelContext.start();
    }
}

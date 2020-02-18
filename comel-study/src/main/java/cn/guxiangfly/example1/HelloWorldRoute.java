package cn.guxiangfly.example1;

import org.apache.camel.builder.RouteBuilder;

/**
 * @Author guxiang02
 * @Date 2019/12/30 16:21
 **/
public class HelloWorldRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        System.out.println("Hello Word IN ");
    }
}

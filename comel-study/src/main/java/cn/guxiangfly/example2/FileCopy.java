package cn.guxiangfly.example2;

import cn.guxiangfly.example1.HelloWorldRoute;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

/**
 * @Author guxiang02
 * @Date 2019/12/30 16:29
 **/
public class FileCopy {
    public static void main(String[] args) throws Exception{
        DefaultCamelContext camelContext = new DefaultCamelContext();


        // 将我们编排的一个完整消息路由过程，加入到上下文中
        camelContext.addRoutes(new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                from("file:input_box?noop=true")
                        .to("file:output_box");
            }
        });

        while (true){
            camelContext.start();
        }
    }
}

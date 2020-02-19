package cn.guxiangfly.example5;

import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

/**
 * @Author guxiang02
 * @Date 2019/12/31 10:55
 **/
public class CallMethodUsingClassComponent {
    public static void main(String[] args) throws Exception{
        DefaultCamelContext camelContext = new DefaultCamelContext();
        camelContext.addRoutes(new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                from("direct:start")
                        .to("class:cn.guxiangfly.example5.MyService?method=doSomething");
            }
        });
        camelContext.start();
        ProducerTemplate producerTemplate = camelContext.createProducerTemplate();
        producerTemplate.sendBody("direct:start", "Hello EveryOne");

    }
}

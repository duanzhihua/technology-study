package cn.guxiangfly.example3;

import org.apache.camel.ConsumerTemplate;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

/**
 * @Author guxiang02
 * @Date 2019/12/30 16:35
 **/
public class ProdAndConsumeExample {
    public static void main(String[] args)  throws Exception{
        DefaultCamelContext camelContext = new DefaultCamelContext();
        // 将我们编排的一个完整消息路由过程，加入到上下文中
        camelContext.addRoutes(new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                from("direct:start")
                        .process(new Processor() {
                            public void process(Exchange exchange) throws Exception {
                                String message = exchange.getIn().getBody(String.class);
                                System.out.println("I am the Processor   "+message);
                            }
                        })
                        .to("seda:end");
            }
        });
        camelContext.start();

        ProducerTemplate producerTemplate = camelContext.createProducerTemplate();
        producerTemplate.sendBody("direct:start","Hello EveryOne");

        ConsumerTemplate consumerTemplate = camelContext.createConsumerTemplate();
        String message = consumerTemplate.receiveBody("seda:end", String.class);

        System.out.println(message);


    }
}

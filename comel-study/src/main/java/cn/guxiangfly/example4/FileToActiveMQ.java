package cn.guxiangfly.example4;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.impl.DefaultCamelContext;

/**
 * @Author guxiang02
 * @Date 2019/12/30 17:24
 **/
public class FileToActiveMQ {
    public static void main(String[] args) throws Exception {
        DefaultCamelContext camelContext = new DefaultCamelContext();
        // 将我们编排的一个完整消息路由过程，加入到上下文中

        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
        camelContext.addComponent("jms", JmsComponent.jmsComponentAutoAcknowledge(connectionFactory));
        camelContext.addRoutes(new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                from("direct:start")
                        .to("activemq:queue:my_queue");
            }
        });

        ProducerTemplate producerTemplate = camelContext.createProducerTemplate();
        producerTemplate.sendBody("direct:start", "Hello EveryOne");

        camelContext.start();


    }
}

package cn.guxiangfly.example4;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.ConsumerTemplate;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.impl.DefaultCamelContext;

/**
 * @Author guxiang02
 * @Date 2019/12/31 10:40
 **/
public class ActiveMqConsumer {

    public static void main(String[] args) throws Exception {
        DefaultCamelContext camelContext = new DefaultCamelContext();
        // 将我们编排的一个完整消息路由过程，加入到上下文中

        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
        camelContext.addComponent("jms", JmsComponent.jmsComponentAutoAcknowledge(connectionFactory));
        camelContext.addRoutes(new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                from("activemq:queue:my_queue")
                        .to("seda:end");
            }
        });

        ConsumerTemplate consumerTemplate = camelContext.createConsumerTemplate();
        String message = consumerTemplate.receiveBody("seda:end", String.class);
        System.out.println(message);

        camelContext.start();


    }
}

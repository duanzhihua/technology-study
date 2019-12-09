package cn.guxiangfly.kafkalearn.chapter1;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

/**
 * @author guxiang (guxiang@rd.netease.com)
 * @date 2019/11/18 10:40
 */
public class ConsumerFastStart {

    /**
     * 注意：1，centos安装Kafka记得关闭防火墙
     *      2，kafka配置文件listeners和advertised.listeners记得写ip地址不要用127.0.0.1和localhost
     *     例如：listeners=PLAINTEXT://10.118.80.53:9092
     *          advertised.listeners=PLAINTEXT://10.118.80.53:9092
     */

    public static final String brokerList = "192.168.25.101:9092";
    public static final String topic = "first";
    public static final String groupId = "allgroup";

    public static void main(String[] args) throws InterruptedException {
        Properties properties = new Properties();
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, brokerList);

        // 设置消费者组的名称
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);

        //创建一个消费者客户端实例
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(properties);

        // 订阅主题
        consumer.subscribe(Collections.singletonList(topic));

        // 循环消费消息
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(3000));
            for (ConsumerRecord<String, String> record : records) {
                System.out.println(record.value());
            }
            Thread.sleep(2000);
        }
    }

}

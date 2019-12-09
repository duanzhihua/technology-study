package cn.guxiangfly.kafkalearn.chapter1;


import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;
import java.util.concurrent.Future;

/**
 * @author guxiang (guxiang@rd.netease.com)
 * @date 2019/11/17 22:53
 */
public class ProducerFastStart {

    private static Logger logger = LoggerFactory.getLogger(ProducerFastStart.class);

    public static final String brokerList = "192.168.25.101:9092";

    public static final String topic = "first";

    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, brokerList);


        // 配置生产者客户端参数并创建kafkaProducer实例
        KafkaProducer<String, String> producer = new KafkaProducer<>(properties);

        ProducerRecord<String, String> record = new ProducerRecord<>(topic, "kafka-test","hello,kafka");

        try {
           Future<RecordMetadata> send = producer.send(record);
           RecordMetadata recordMetadata = send.get();
           System.out.println("topic:" +recordMetadata.topic());
           System.out.println("offset:" +recordMetadata.offset());
           System.out.println("partition:" +recordMetadata.partition());

            producer.send(record, new Callback() {
                @Override
                public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                    System.out.println(recordMetadata.partition() +":" + recordMetadata.offset());
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }

        producer.close();
    }
}

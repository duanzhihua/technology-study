package cn.guxiangfly.kafkalearn.interceptor;

import org.apache.kafka.common.Configurable;

/**
 * @author guxiang (guxiang@rd.netease.com)
 * @date 2019/11/18 16:09
 */
public interface ProducerInterceptor<K,V> extends Configurable {

}

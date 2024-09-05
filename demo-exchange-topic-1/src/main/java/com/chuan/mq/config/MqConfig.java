package com.chuan.mq.config;

import jakarta.annotation.Resource;
import org.springframework.amqp.core.*;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableConfigurationProperties(MqConfigProperties.class)
@Configuration
public class MqConfig {

    @Resource
    private MqConfigProperties mqConfigProperties;

    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange(mqConfigProperties.getExchangeName(), true, false);
    }

    @Bean
    public Queue queueA() {
        return new Queue(mqConfigProperties.getQueueAName(),true);
    }

    @Bean
    public Queue queueB() {
        return new Queue(mqConfigProperties.getQueueBName(),true);
    }

    @Bean
    public Binding bindingA(TopicExchange topicExchange, Queue queueA) {
        return BindingBuilder.bind(queueA).to(topicExchange).with("*.orange.*");
    }

    @Bean
    public Binding bindingB(TopicExchange topicExchange, Queue queueB) {
        return BindingBuilder.bind(queueB).to(topicExchange).with("*.*.rabbit");
    }


}

package com.chuan.boot.config;

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
    public DirectExchange normalExchange() {
        return new DirectExchange(mqConfigProperties.getExchangeName());
    }

    @Bean
    public DirectExchange deadExchange() {
        return new DirectExchange(mqConfigProperties.getExchangeName());
    }

    @Bean
    public Queue normalQueueA() {
        return QueueBuilder.durable(mqConfigProperties.getNormalQueueNameA())
                .deadLetterExchange(mqConfigProperties.getExchangeName())
                .deadLetterRoutingKey(mqConfigProperties.getDeadRoutingKey())
                .ttl(20000)
                .build();
    }

    @Bean
    public Queue normalQueueB() {
        return QueueBuilder.durable(mqConfigProperties.getNormalQueueNameB())
                .deadLetterExchange(mqConfigProperties.getExchangeName())
                .deadLetterRoutingKey(mqConfigProperties.getDeadRoutingKey())
                .ttl(10000)
                .build();
    }

    @Bean
    public Queue deadQueue() {
        return QueueBuilder.durable(mqConfigProperties.getDeadQueueName()).build();
    }

    @Bean
    public Binding bindingNormalA() {
        return BindingBuilder.bind(normalQueueA()).to(normalExchange()).with(mqConfigProperties.getNormalRoutingKeyA());
    }

    @Bean
    public Binding bindingNormalB() {
        return BindingBuilder.bind(normalQueueB()).to(normalExchange()).with(mqConfigProperties.getNormalRoutingKeyB());
    }

    @Bean
    public Binding bindingDead() {
        return BindingBuilder.bind(deadQueue()).to(deadExchange()).with(mqConfigProperties.getDeadRoutingKey());
    }


}

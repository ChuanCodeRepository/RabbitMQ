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
        return new DirectExchange(mqConfigProperties.getNormalExchangeName());
    }

    @Bean
    public DirectExchange deadExchange() {
        return new DirectExchange(mqConfigProperties.getDeadExchangeName());
    }

    @Bean
    public Queue normalQueue() {
        return QueueBuilder.durable(mqConfigProperties.getNormalQueueName())
                .deadLetterExchange(mqConfigProperties.getDeadExchangeName())
                .deadLetterRoutingKey(mqConfigProperties.getDeadRoutingKey())
                .build();
    }

    @Bean
    public Queue deadQueue() {
        return QueueBuilder.durable(mqConfigProperties.getDeadQueueName()).build();
    }

    @Bean
    public Binding bindingNormal() {
        return BindingBuilder.bind(normalQueue()).to(normalExchange()).with(mqConfigProperties.getNormalRoutingKey());
    }

    @Bean
    public Binding bindingDead() {
        return BindingBuilder.bind(deadQueue()).to(deadExchange()).with(mqConfigProperties.getDeadRoutingKey());
    }


}

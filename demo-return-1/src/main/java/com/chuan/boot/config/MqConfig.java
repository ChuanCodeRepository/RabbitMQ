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
    public DirectExchange directExchange() {
       return new DirectExchange(mqConfigProperties.getExchangeName(), true, false);
    }

    @Bean
    public Queue queue() {
        return new Queue(mqConfigProperties.getQueueName(), true,false, false);
    }

    @Bean
    public Binding binding() {
        return BindingBuilder.bind(queue()).to(directExchange()).with(mqConfigProperties.getRoutingKey());
    }

}

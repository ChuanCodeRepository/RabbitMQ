package com.chuan.boot.config;

import jakarta.annotation.Resource;
import org.springframework.amqp.core.*;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.HashMap;

@EnableConfigurationProperties(MqConfigProperties.class)
@Configuration
public class MqConfig {

    @Resource
    private MqConfigProperties mqConfigProperties;

    @Bean
    public CustomExchange customExchange() {
        //延迟交换机的内部使用的是哪种交换机
        HashMap<String, Object> args = new HashMap<>(1);
        args.put("x-delayed-type", "direct");

        return new CustomExchange(mqConfigProperties.getDelayExchangeName(),
                "x-delayed-message",true,false,args);
    }

    @Bean
    public Queue queue() {
        return QueueBuilder.durable(mqConfigProperties.getDelayQueueName())
                .build();
    }

    @Bean
    public Binding binding() {
        return BindingBuilder.bind(queue()).to(customExchange()).with(mqConfigProperties.getDelayRoutingKey()).noargs();
    }


}

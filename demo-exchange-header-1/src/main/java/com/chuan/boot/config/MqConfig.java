package com.chuan.boot.config;

import jakarta.annotation.Resource;
import org.springframework.amqp.core.*;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.HashMap;
import java.util.Map;

@EnableConfigurationProperties(MqConfigProperties.class)
@Configuration
public class MqConfig {

    @Resource
    private MqConfigProperties mqConfigProperties;

    @Bean
    public HeadersExchange headersExchange() {
        return ExchangeBuilder.headersExchange(mqConfigProperties.getExchangeName())
                .durable(true).build();
    }

    @Bean
    public Queue queueA() {
        return QueueBuilder.durable(mqConfigProperties.getQueueAName()).build();
    }

    @Bean
    public Queue queueB() {
        return QueueBuilder.durable(mqConfigProperties.getQueueBName()).build();
    }

    @Bean
    public Binding bindingA(HeadersExchange headersExchange, Queue queueA) {
        Map<String,Object> args = new HashMap<>(2);
        args.put("color","red");
        args.put("speed","fast");
        return BindingBuilder.bind(queueA).to(headersExchange).whereAll(args).match();
    }

    @Bean
    public Binding bindingB(HeadersExchange headersExchange, Queue queueB) {
        return BindingBuilder.bind(queueB).to(headersExchange).where("color").exists();
    }


}

package com.chuan.boot.config;

import jakarta.annotation.Resource;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
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
    public DirectExchange directExchange() {
        return new DirectExchange(mqConfigProperties.getExchangeName(),true,false);
    }

    @Bean
    public Queue queueA() {
        // 队列A的过期时间设置为5秒,队列的过期时间决定了在没有任何消费者的情况下,队列中的消息可以存活多久;
        // 如果消息和对列都设置过期时间,则消息的 TTL 以两者之间较小的那个数值为准.
        HashMap<String, Object> args = new HashMap<>(1);
        args.put("x-message-ttl", 5000);
        return new Queue(mqConfigProperties.getQueueAName(),true,false,false,args);
    }

    @Bean
    public Queue queueB() {
        return new Queue(mqConfigProperties.getQueueBName(),true);
    }

    @Bean
    public Binding bindingA(DirectExchange directExchange) {
        return BindingBuilder.bind(queueA()).to(directExchange).with(mqConfigProperties.getRoutingKey());
    }

    //@Bean
    //public Binding bindingB(DirectExchange directExchange) {
    //    return BindingBuilder.bind(queueB()).to(directExchange).with(mqConfigProperties.getRoutingKey());
    //}

}

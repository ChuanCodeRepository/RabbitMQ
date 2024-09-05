package com.chuan.boot.config;

import jakarta.annotation.Resource;
import org.springframework.amqp.core.*;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

@EnableConfigurationProperties(MqConfigproperties.class)
@Configuration
public class MqConfig {

    @Resource
    private MqConfigproperties mqConfigproperties;


    @Bean
    public DirectExchange normalExchange() {
        return new DirectExchange(mqConfigproperties.getNormalExchangeName(), true, false);
    }

    @Bean
    public DirectExchange deadExchange() {
        return new DirectExchange(mqConfigproperties.getDeadExchangeName(), true, false);
    }

    @Bean
    public Queue queueNormal() {
        HashMap<String, Object> args = new HashMap<>(2);
        // 指定死信交换机,通过x-dead-letter-exchange 来设置
        args.put("x-dead-letter-exchange", mqConfigproperties.getDeadExchangeName());
        // 设置死信交换机和死信队列的路由key,value为死信交换机和死信队列绑定的key.要一模一样,因为死信交换机是直连交换机.
        args.put("x-dead-letter-routing-key", mqConfigproperties.getDeadRoutingKey());
        //队列的过期时间,代表的是每条消息进入队列后经过多少时间,就会进入死信队列中.
        //例如a消息和b消息同时进入过期时间为10s的队列,a消息比b消息提前5s进入队列
        //a消息再过5s即过期,b消息要过10s过期,过期后分别进入死信队列
        args.put("x-message-ttl",Integer.valueOf(mqConfigproperties.getDeadMaxMilSecond()));
        return new Queue(mqConfigproperties.getNormalQueueName(), true, false, false, args);
    }

    @Bean
    public Queue queueDead() {
        return new Queue(mqConfigproperties.getDeadQueueName(), true);
    }

    @Bean
    public Binding bindingNormal() {
        return BindingBuilder.bind(queueNormal()).to(normalExchange()).with(mqConfigproperties.getNormalRoutingKey());
    }

    @Bean
    public Binding bindingDead() {
        return BindingBuilder.bind(queueDead()).to(deadExchange()).with(mqConfigproperties.getDeadRoutingKey());
    }


}

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
        // 设置队列的最大长度,消息数超过该长度,会把先进入还没处理完的消息挤出去,导致进入死信队列.
        args.put("x-max-length", 5);
        return new Queue(mqConfigproperties.getNormalQueueName(), true, false, false,args);
    }

    @Bean
    public Queue queueDead() {
        return new Queue(mqConfigproperties.getDeadQueueName(), true,false, false);
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

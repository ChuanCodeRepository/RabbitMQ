package com.chuan.mq.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {

    /*
    * 1.定义交换机
    * 2.定义队列
    * 3.绑定交换机和队列
    * */

    @Bean
    public DirectExchange directExchange(){
        //直连交换机需要路由key,消息发送到路由key指向的队列.
        return new DirectExchange("exchange.direct");
    }

    @Bean
    public Queue queueA(){
        return new Queue("queue.direct.A");
    }

    @Bean
    public Queue queueB(){
        return new Queue("queue.direct.B");
    }

    @Bean
    public Binding bindingA(DirectExchange directExchange, Queue queueA){
        return BindingBuilder.bind(queueA).to(directExchange).with("direct.A");
    }

    @Bean
    public Binding bindingB(DirectExchange directExchange, Queue queueB){
        return BindingBuilder.bind(queueB).to(directExchange).with("direct.A");
    }

}

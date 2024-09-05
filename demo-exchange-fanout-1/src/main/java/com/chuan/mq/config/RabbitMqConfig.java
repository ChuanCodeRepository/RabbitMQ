package com.chuan.mq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
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
    public FanoutExchange fanoutExchange(){
        //扇形交换机不需要路由key,消息发送到所有绑定的队列,相当于广播.
        return new FanoutExchange("exchange.fanout");
    }

    @Bean
    public Queue queueA(){
        return new Queue("queue.fanout.A");
    }

    @Bean
    public Queue queueB(){
        return new Queue("queue.fanout.B");
    }

    @Bean
    public Binding bindingA(FanoutExchange fanoutExchange, Queue queueA){
        return BindingBuilder.bind(queueA).to(fanoutExchange);
    }

    @Bean
    public Binding bindingB(FanoutExchange fanoutExchange, Queue queueB){
        return BindingBuilder.bind(queueB).to(fanoutExchange);
    }

}

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
    public DirectExchange exchange() {
        HashMap<String, Object> args = new HashMap<>(1);
        args.put("alternate-exchange", mqConfigproperties.getBackupExchangeName());

        return new DirectExchange(mqConfigproperties.getExchangeName(), true, false, args);
    }

    @Bean
    public FanoutExchange backupExchange() {
        return new FanoutExchange(mqConfigproperties.getBackupExchangeName(), true, false);
    }

    @Bean
    public Queue queue() {
        return new Queue(mqConfigproperties.getQueueName(), true, false, false);
    }

    @Bean
    public Queue backupQueue() {
        return new Queue(mqConfigproperties.getBackupQueueName(), true, false, false);
    }

    @Bean
    public Binding binding() {
        return BindingBuilder.bind(queue()).to(exchange()).with(mqConfigproperties.getRoutingKey());
    }

    @Bean
    public Binding bindingBackup() {
        return BindingBuilder.bind(backupQueue()).to(backupExchange());
    }


}

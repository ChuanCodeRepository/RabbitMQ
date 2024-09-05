package com.chuan.boot.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Setter
@Getter
@ConfigurationProperties("mq")
public class MqConfigproperties {

    private String exchangeName;

    private String backupExchangeName;

    private String queueName;

    private String backupQueueName;

    private String routingKey;

    private String backupRoutingKey;

}

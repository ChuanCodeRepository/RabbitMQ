package com.chuan.boot.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("mq")
@Getter
@Setter
public class MqConfigProperties {

    private String normalExchangeName;

    private String deadExchangeName;

    private String normalQueueName;

    private String deadQueueName;

    private String normalRoutingKey;

    private String deadRoutingKey;

}

package com.chuan.boot.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Setter
@Getter
@ConfigurationProperties("mq")
public class MqConfigproperties {

    private String normalExchangeName;

    private String deadExchangeName;

    private String normalQueueName;

    private String deadQueueName;

    private String normalRoutingKey;

    private String deadRoutingKey;

    private String deadMaxMilSecond;
}

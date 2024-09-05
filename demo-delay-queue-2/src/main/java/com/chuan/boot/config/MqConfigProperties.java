package com.chuan.boot.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("mq")
@Getter
@Setter
public class MqConfigProperties {

    private String exchangeName;

    private String normalQueueNameA;

    private String normalQueueNameB;

    private String deadQueueName;

    private String normalRoutingKeyA;

    private String normalRoutingKeyB;

    private String deadRoutingKey;

}

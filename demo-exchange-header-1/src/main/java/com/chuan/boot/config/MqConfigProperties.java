package com.chuan.boot.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Setter
@Getter
@ConfigurationProperties("mq")
public class MqConfigProperties {

    private String exchangeName;

    private String queueAName;

    private String queueBName;

}

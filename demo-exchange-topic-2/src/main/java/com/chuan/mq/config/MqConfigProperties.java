package com.chuan.mq.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Setter
@Getter
@ConfigurationProperties("mq")
public class MqConfigProperties {

    private String exchangeName;

    public String queueAName;

    public String queueBName;

}

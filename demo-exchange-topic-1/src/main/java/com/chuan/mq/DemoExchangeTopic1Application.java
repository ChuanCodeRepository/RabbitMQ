package com.chuan.mq;

import com.chuan.mq.config.MqConfig;
import com.chuan.mq.config.MqConfigProperties;
import com.chuan.mq.service.MessageService;
import jakarta.annotation.Resource;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
public class DemoExchangeTopic1Application implements ApplicationRunner {

    @Resource
    private MessageService messageService;
    public static void main(String[] args) {
        SpringApplication.run(DemoExchangeTopic1Application.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        messageService.sendMessage("hello world");

    }

}

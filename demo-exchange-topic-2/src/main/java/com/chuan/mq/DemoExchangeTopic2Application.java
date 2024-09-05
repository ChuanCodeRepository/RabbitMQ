package com.chuan.mq;

import com.chuan.mq.service.MessageService;
import jakarta.annotation.Resource;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoExchangeTopic2Application {

    public static void main(String[] args) {
        SpringApplication.run(DemoExchangeTopic2Application.class, args);
    }
}

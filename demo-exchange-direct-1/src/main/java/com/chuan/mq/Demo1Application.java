package com.chuan.mq;

import com.chuan.mq.service.MessageService;
import jakarta.annotation.Resource;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Demo1Application implements ApplicationRunner {

    @Resource
    private MessageService messageService;

    public static void main(String[] args) {
        SpringApplication.run(Demo1Application.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        messageService.sendMessage();
    }
}

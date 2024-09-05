package com.chuan.boot;

import com.chuan.boot.service.MessageSendService;
import jakarta.annotation.Resource;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoConfirm1Application implements ApplicationRunner {

    @Resource
    private MessageSendService messageSendService;

    public static void main(String[] args) {
        SpringApplication.run(DemoConfirm1Application.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        messageSendService.sendMessage("hello world");
    }

}

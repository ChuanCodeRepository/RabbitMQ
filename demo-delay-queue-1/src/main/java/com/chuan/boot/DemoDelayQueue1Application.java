package com.chuan.boot;

import com.chuan.boot.service.MessageSendService;
import jakarta.annotation.Resource;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoDelayQueue1Application implements ApplicationRunner {

    @Resource
    private MessageSendService messageSendService;

    public static void main(String[] args) {
        SpringApplication.run(DemoDelayQueue1Application.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        for (int i = 5; i > 2; i--) {

            messageSendService.sendMessage("delay message " + i, String.valueOf(i * 4000));

        }

    }
}

package com.chuan.boot;

import com.chuan.boot.service.MessageSendService;
import jakarta.annotation.Resource;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoDelayQueuePluginApplication implements ApplicationRunner {

    @Resource
    private MessageSendService messageSendService;

    public static void main(String[] args) {
        SpringApplication.run(DemoDelayQueuePluginApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        messageSendService.sendMessage("A", 15000);

        messageSendService.sendMessage("B", 5000);

    }
}

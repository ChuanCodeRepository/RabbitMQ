package com.chuan.boot;

import com.chuan.boot.service.MessageService;
import jakarta.annotation.Resource;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 设置消息队列的最大长度, 超过这个长度的消息会被丢弃,进入死信队列.
 */
@SpringBootApplication
public class DemoExchangeDlx3Application implements ApplicationRunner {

    @Resource
    private MessageService messageService;

    public static void main(String[] args) {
        SpringApplication.run(DemoExchangeDlx3Application.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

            for (int i = 0; i < 5; i++) {
                messageService.sendMessage("hello world " + i);
            }

    }

}

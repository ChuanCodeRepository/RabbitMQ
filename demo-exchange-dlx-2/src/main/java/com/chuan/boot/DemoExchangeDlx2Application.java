package com.chuan.boot;

import com.chuan.boot.service.MessageService;
import jakarta.annotation.Resource;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 设置每条消息的过期时间,测试死信队列
 */
@SpringBootApplication
public class DemoExchangeDlx2Application implements ApplicationRunner {

    @Resource
    private MessageService messageService;

    public static void main(String[] args) {
        SpringApplication.run(DemoExchangeDlx2Application.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        messageService.sendMessage("1");

        Thread.sleep(10000);

        messageService.sendMessage("2");
    }
}

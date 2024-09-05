package com.chuan.boot;

import com.chuan.boot.service.MessageService;
import jakarta.annotation.Resource;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Time;
import java.util.concurrent.TimeUnit;

/**
 * 设置队列过期时间,测试死信队列
 */
@SpringBootApplication
public class DemoExchangeDlx1Application implements ApplicationRunner {

    @Resource
    private MessageService messageService;

    public static void main(String[] args) {
        SpringApplication.run(DemoExchangeDlx1Application.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        messageService.sendMessage("1");

        TimeUnit.SECONDS.sleep(20);

        messageService.sendMessage("2");

    }
}

package com.chuan.boot;

import com.chuan.boot.config.MqConfigProperties;
import com.chuan.boot.service.MessageSendService;
import jakarta.annotation.Resource;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoDelayQueue2Application implements ApplicationRunner {

    @Resource
    private MessageSendService messageSendService;

    @Resource
    private MqConfigProperties mqConfigProperties;

    public static void main(String[] args) {
        SpringApplication.run(DemoDelayQueue2Application.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        //分别对AB队列发送两条消息,同个队列的消息过期时间是相等的,会按顺序进入到死信队列
        messageSendService.sendMessage("hello world A 1",mqConfigProperties.getNormalRoutingKeyA());
        messageSendService.sendMessage("hello world B 1",mqConfigProperties.getNormalRoutingKeyB());
        messageSendService.sendMessage("hello world A 2",mqConfigProperties.getNormalRoutingKeyA());
        messageSendService.sendMessage("hello world B 2",mqConfigProperties.getNormalRoutingKeyB());

    }

}

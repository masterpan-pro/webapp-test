package com.demo.activemq;

import lombok.extern.slf4j.Slf4j;

import javax.jms.Message;
import javax.jms.MessageListener;

@Slf4j
public class TopicMessageListen implements MessageListener {

    @Override
    public void onMessage(Message message) {
        try {
            log.info("message: {}", message);
            String name = message.getStringProperty("name");
            log.info("name: {}", name);
        } catch (Exception e) {
            log.error("{}", e);
        }
    }
}

package com.hb.tool.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("messageSender")
public class MessageSender {

    private Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private AmqpTemplate amqpTemplate;

    public void send(Object message) {
        this.amqpTemplate.convertAndSend(message);
        LOGGER.info(String.format("Send Message: [%s] success", new Object[]{message}));
    }

    public void send(String routingKey, Object message) {
        this.amqpTemplate.convertAndSend(routingKey, message);
        LOGGER.info(String.format("Send Message [%s] to Routing [%s] success", new Object[]{message, routingKey}));
    }

    public Object sendAndReceive(Object message) {
        LOGGER.info(String.format("Send Message: [%s]", new Object[]{message}));
        Object response = this.amqpTemplate.convertSendAndReceive(message);
        LOGGER.info(String.format("Send success. Responsed Message: [%s]", new Object[]{response}));
        return response;
    }

    public Object sendAndReceive(String routingKey, Object message) {
        LOGGER.info(String.format("Send to Routing [%s], Message: [%s]", new Object[]{routingKey, message}));
        Object response = this.amqpTemplate.convertSendAndReceive(routingKey, message);
        LOGGER.info(String.format("Send to Routing [%s] success. Responsed Message: [%s]", new Object[]{routingKey, response}));
        return response;
    }

}

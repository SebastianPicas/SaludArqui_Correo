package com.saludarqui.correos.listener;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class EventListener {

    @RabbitListener
    public void notificationListener() {

    }
}

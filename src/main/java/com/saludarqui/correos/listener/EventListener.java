package com.saludarqui.correos.listener;

import com.saludarqui.correos.logica.NotificationService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EventListener {

    @Autowired
    private NotificationService notificationService;

    @RabbitListener(queues = "RegistroBeneficiario")
    public void receiveMessage(Long idBeneficiario) {
        System.out.println("Received idBeneficiario: " + idBeneficiario);
        notificationService.sendAfiliadoNotification(idBeneficiario);
    }
}

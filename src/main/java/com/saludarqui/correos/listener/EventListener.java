package com.saludarqui.correos.listener;

import com.saludarqui.correos.logica.NotificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class EventListener {

    @Autowired
    private NotificationService notificationService;

    private static final int MAX_ATTEMPTS = 3;
    private static final long RETRY_DELAY = 5000; // 5 segundos en milisegundos

    @RabbitListener(queues = "RegistroBeneficiario")
    public void receiveMessage(Long idBeneficiario) {
        log.info("Received idBeneficiario: {}", idBeneficiario);
        boolean success = false;

        for (int attempt = 1; attempt <= MAX_ATTEMPTS; attempt++) {
            try {
                notificationService.sendAfiliadoNotification(idBeneficiario);
                success = true;
                log.info("Mensaje procesado exitosamente en el intento {}", attempt);
                break;
            } catch (Exception e) {
                log.error("Error al procesar el mensaje (intento {} de {}): {}", attempt, MAX_ATTEMPTS, e.getMessage(), e);

                if (attempt < MAX_ATTEMPTS) {
                    try {
                        Thread.sleep(RETRY_DELAY);
                    } catch (InterruptedException interruptedException) {
                        Thread.currentThread().interrupt();
                        log.error("El hilo fue interrumpido durante el retraso entre reintentos", interruptedException);
                        break;
                    }
                }
            }
        }

        if (!success) {
            log.error("El mensaje no pudo procesarse después de {} intentos. idBeneficiario: {}", MAX_ATTEMPTS, idBeneficiario);

        }
    }
}

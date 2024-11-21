package com.saludarqui.correos.logica;

import com.saludarqui.correos.db.jpa.BeneficiarioJPA;
import com.saludarqui.correos.db.orm.AfiliadoORM;
import com.saludarqui.correos.db.orm.BeneficiarioORM;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Slf4j
@Service
public class NotificationService {

    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    private BeneficiarioJPA beneficiarioJPA;

    private static final Logger logger = Logger.getLogger(NotificationService.class.getName());

    public void sendAfiliadoNotification(Long idBeneficiario) {
        BeneficiarioORM beneficiario = beneficiarioJPA.findById(idBeneficiario)
                .orElseThrow(() -> new RuntimeException("Beneficiario no encontrado"));

        AfiliadoORM afiliado = beneficiario.getAfliliadoORM();

        if (afiliado != null) {
            String email = afiliado.getEmail();
            String subject = "Notificación de Afiliado";
            String message = "Hola " + afiliado.getNombre() + ",\n\n" +
                    "Su ID de afiliado es: " + afiliado.getIdAfiliado() + ".\n\n" + "Se a registrado un beneficiario con codigo:"
                    + beneficiario.getIdBeneficiario() + "y nombre"+ beneficiario.getNombre() + ",\n\n"
                    + "Gracias por ser parte de nuestro sistema.";

            sendEmail(email, subject, message);
            log.info("Correo enviado exitosamente a: {}", email);
        } else {
            log.warn("No se encontró un afiliado asociado para el beneficiario con ID: {}", idBeneficiario);
        }
    }

    private void sendEmail(String to, String subject, String text) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(to);
            message.setSubject(subject);
            message.setText(text);
            emailSender.send(message);
            log.info("Correo enviado a: {}", to);
        } catch (Exception e) {
            log.error("Error al enviar el correo: {}", e.getMessage());
        }
    }
}

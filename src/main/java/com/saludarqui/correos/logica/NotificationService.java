package com.saludarqui.correos.logica;

import com.saludarqui.correos.db.jpa.BeneficiarioJPA;
import com.saludarqui.correos.db.orm.AfiliadoORM;
import com.saludarqui.correos.db.orm.BeneficiarioORM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

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
                    "Su ID de afiliado es: " + afiliado.getIdAfiliado() + ".\n\n" +
                    "Gracias por ser parte de nuestro sistema.";

            sendEmail(email, subject, message);
            logger.info("Correo enviado exitosamente a: " + email);
        } else {
            logger.warning("No se encontró un afiliado asociado para el beneficiario con ID: " + idBeneficiario);
        }
    }

    private void sendEmail(String to, String subject, String text) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(to);
            message.setSubject(subject);
            message.setText(text);
            emailSender.send(message);
            logger.info("Correo enviado a: " + to);
        } catch (Exception e) {
            logger.severe("Error al enviar el correo: " + e.getMessage());
        }
    }
}

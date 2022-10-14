package com.dent.dentclinicapp.service;

import com.dent.dentclinicapp.domain.AppointmentDto;
import com.dent.dentclinicapp.domain.Mail;
import com.dent.dentclinicapp.proxy.ProxyInterface;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class SimpleEmailService
{
    private final JavaMailSender javaMailSender;

    @Autowired
    private MailCreatorService mailCreatorService;
    private static final String SUBJECT = "Your dentist appointment.";

    public void sendAppointmentEmail(AppointmentDto appointmentDto) {
        send(new Mail(appointmentDto.getEmail(), SUBJECT), appointmentDto);
    }

    public void send(final Mail mail, final AppointmentDto appointmentDto) {
        log.info("Starting email preparation...");
        try {
            javaMailSender.send(createMimeAppointmentMessage(mail, appointmentDto));
            log.info("Email has been sent.");
        } catch (MailException e) {
            log.error("Failed to process email sending: " + e.getMessage(), e);
        }
    }

    private MimeMessagePreparator createMimeAppointmentMessage(final Mail mail, final AppointmentDto appointmentDto) {
        return mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setTo(mail.getMailTo());
            messageHelper.setSubject(mail.getSubject());
            messageHelper.setText(mailCreatorService.buildAppointmentEmail(appointmentDto), true);
        };
    }
}

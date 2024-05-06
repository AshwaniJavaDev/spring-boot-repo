package com.email.sender.service;

import com.email.sender.model.EmailSenderModel;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.nio.charset.StandardCharsets;

@Service
public class EmailSenderService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private TemplateEngine templateEngine;

    public void sendEmailTemplate(EmailSenderModel templateModel) throws MessagingException {

        Context context = getThymeleafContext(templateModel);
        String emailBody = templateEngine.process("email-template-body", context);
        sendTemplateBodyEmail(templateModel, "Email Template", emailBody);

    }

    private void sendTemplateBodyEmail(EmailSenderModel templateModel, String subject, String body) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper messageHelper =
                new MimeMessageHelper(mimeMessage,
                        MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                        StandardCharsets.UTF_8.name());

        messageHelper.setTo(templateModel.getEmail());
        messageHelper.setSubject(subject);
        messageHelper.setText(body, true);
        javaMailSender.send(mimeMessage);
    }

    private Context getThymeleafContext(EmailSenderModel templateModel) {

        Context thymeContext = new Context();
        thymeContext.setVariable("fullname", templateModel.getName());
        thymeContext.setVariable("email", templateModel.getEmail());
        thymeContext.setVariable("phone", templateModel.getPhone());

        thymeContext.setVariable("address", templateModel.getAddress().getAddress());
        thymeContext.setVariable("city", templateModel.getAddress().getCity());
        thymeContext.setVariable("pincode", templateModel.getAddress().getPincode());
        thymeContext.setVariable("state", templateModel.getAddress().getState());

        thymeContext.setVariable("totalUsage", templateModel.getCharges().getTotalUsage());
        thymeContext.setVariable("totalHours", templateModel.getCharges().getTotalHours());
        thymeContext.setVariable("usageRate", templateModel.getCharges().getUsageRate());

        return thymeContext;
    }
}

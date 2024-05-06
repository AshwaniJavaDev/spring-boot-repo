package com.email.sender.controller;

import com.email.sender.model.Address;
import com.email.sender.model.Charges;
import com.email.sender.model.EmailSenderModel;
import com.email.sender.service.EmailSenderService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {

    @Autowired
    private EmailSenderService emailSenderService;

    @GetMapping("/email-send")
    public ResponseEntity<String> sendEmailTemplate() throws MessagingException {
        Address address = new Address("H-50, A.V. Nagar", "New Delhi", "110049", "Delhi");
        Charges charges = new Charges(200, 10, 20);

        EmailSenderModel templateModel =
                new EmailSenderModel(
                        "Ashwani",
                        "ashwanikmr212@gmail.com",
                        "9879879879",
                        address,
                        charges);
        emailSenderService.sendEmailTemplate(templateModel);
        return new ResponseEntity<>("Email Send Successfully", HttpStatus.OK);
    }
}

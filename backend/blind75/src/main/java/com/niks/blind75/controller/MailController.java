package com.niks.blind75.controller;

import com.niks.blind75.model.EmailResponse;
import com.niks.blind75.model.EmailStructure;
import com.niks.blind75.service.EmailClient;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/mail")
public class MailController {

    @Autowired
    private EmailClient emailClient;

    @PostMapping("/send")
    public EmailResponse sendMail(@RequestBody List<EmailStructure> emailsToSend){
        EmailResponse emailResponse = emailClient.sendMails(emailsToSend);
       return emailResponse;
    }

}

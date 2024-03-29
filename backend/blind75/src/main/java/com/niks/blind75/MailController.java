package com.niks.blind75;

import com.niks.blind75.model.EmailResponse;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import java.util.List;

@RestController
@RequestMapping("/mail")
public class MailController {

    @Autowired
    private  EmailClient emailClient;

    @PostMapping("/send")
    public EmailResponse sendMail(@RequestBody List<String> recipients){
        EmailResponse emailResponse = emailClient.sendMails(recipients);
       return emailResponse;
    }

//    public static void main(String[] args) throws MessagingException {
//        EmailClient.sendMail("nani.cinihero@gmail.com");
//    }
}

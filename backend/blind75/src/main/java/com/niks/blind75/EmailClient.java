package com.niks.blind75;

import com.niks.blind75.model.EmailResponse;
import com.niks.blind75.model.EmailStructure;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

@Service
public class EmailClient {
    @Value("${spring.mail.host.username}")
    private String userName;

    @Value("${spring.mail.host.password}")
    private String password;

    public EmailResponse sendMails(List<EmailStructure> emailsToSend){
        EmailResponse emailResponse = new EmailResponse();
        List<EmailStructure> emails = new ArrayList<>();
        for (EmailStructure mailToSend : emailsToSend) {
            EmailStructure sentEmail = EmailClient.sendMail(mailToSend);
            emails.add(sentEmail);
        }
        emailResponse.setEmails(emails);
        return emailResponse;
    }
    public static EmailStructure sendMail(EmailStructure mailToSend) {

       // boolean sent = false;
        Properties props = new Properties();
        Message messageEmpty = null;
        props.put("mail.smtp.auth", "true");//Outgoing server requires authentication
        props.put("mail.smtp.starttls.enable", "true");//TLS must be activated
        props.put("mail.smtp.host", "smtp.gmail.com"); //Outgoing server (SMTP) - change it to your SMTP server
        props.put("mail.smtp.port", "587");//Outgoing port

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
           @Override
            protected PasswordAuthentication getPasswordAuthentication(){
                return new PasswordAuthentication("sainikhil.amaravadi@gmail.com", "");
            }

        });
        try {
            Message message = prepareEmailMessage(session, mailToSend);
            Transport.send(message);
            mailToSend.setSubject(message.getSubject());
            mailToSend.setRecipient(Arrays.toString(message.getRecipients(Message.RecipientType.TO)));
            mailToSend.setBody(message.getSubject());
            mailToSend.setStatus("sent");
            return mailToSend;
        }
        catch (Exception e){
            System.out.println("Failed to send email");
            mailToSend.setStatus("failed");
            return mailToSend;
        }

    }

    private static Message prepareEmailMessage(Session session, EmailStructure mailToSend)
            throws MessagingException {
        Message mimeMessage = new MimeMessage(session);
        mimeMessage.setFrom(new InternetAddress("sainikhil.amaravadi@gmail.com"));
        mimeMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mailToSend.getRecipient()));
        mimeMessage.setSubject(mailToSend.getSubject());
        mimeMessage.setText(mailToSend.getBody());
        return mimeMessage;
    }

  }

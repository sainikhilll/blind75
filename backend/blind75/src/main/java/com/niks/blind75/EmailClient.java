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

    public EmailResponse sendMails(List<String> recipients){
        EmailResponse emailResponse = new EmailResponse();
        List<EmailStructure> emails = new ArrayList<>();
        for (String recipient :recipients) {
            EmailStructure sentEmail = EmailClient.sendMail(recipient);
            emails.add(sentEmail);
        }
        emailResponse.setEmails(emails);
        return emailResponse;
    }
    public static EmailStructure sendMail(String to) {

       // boolean sent = false;
        Properties props = new Properties();
        EmailStructure email = new EmailStructure();
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
            Message message = prepareEmailMessage(session, to);
            Transport.send(message);
            email.setSubject(message.getSubject());
            email.setRecipient(Arrays.toString(message.getRecipients(Message.RecipientType.TO)));
            email.setBody(message.getSubject());
            email.setStatus("sent");
            return email;
        }
        catch (Exception e){
            System.out.println("Failed to send email");
            email.setStatus("failed");
            return email;
        }

    }

    private static Message prepareEmailMessage(Session session, String to)
            throws MessagingException {
        Message mimeMessage = new MimeMessage(session);
        mimeMessage.setFrom(new InternetAddress("sainikhil.amaravadi@gmail.com"));
        mimeMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
        mimeMessage.setSubject("Test from Java Application");
        mimeMessage.setText("Hi, This is computer generated email");
        return mimeMessage;
    }

  }

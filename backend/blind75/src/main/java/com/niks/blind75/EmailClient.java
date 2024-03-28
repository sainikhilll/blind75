package com.niks.blind75;

import org.springframework.beans.factory.annotation.Value;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailClient {
    @Value("${spring.mail.host.username}")
    private static String userName;

    @Value("${spring.mail.host.password}")
    private static String password;

    public static void sendMail(String to) throws MessagingException {
        Properties props = new Properties();
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

        Message message = prepareEmailMessage(session, to);
        Transport.send(message);
        System.out.println("Done");
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

    public static void main(String[] args) throws MessagingException {
        EmailClient.sendMail("nani.cinihero@gmail.com");
}}

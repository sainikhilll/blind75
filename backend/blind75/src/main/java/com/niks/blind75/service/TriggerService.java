package com.niks.blind75.service;

import com.niks.blind75.model.EmailResponse;
import com.niks.blind75.model.EmailStructure;
import com.niks.blind75.model.Problem;
import com.niks.blind75.model.Subscriber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TriggerService {

    @Autowired
    EmailClient emailClient;

    @Autowired
    ProblemService problemService;

    @Autowired
    SubscriberService subscriberService;

    @Autowired
    CustomSubscriberService customSubscriberService;

    public EmailResponse triggerEmails(){
        List<Problem> problems = problemService.getAllProblems();
        List<Subscriber> subscribers = subscriberService.getAllSubscribers();
        List<EmailStructure> emails = new ArrayList<>();

        for (Subscriber sub: subscribers) {
            EmailStructure email = new EmailStructure();
          //  int day = subscribers.stream().filter(subscriber -> problem.getDay() ==  )
            List<Problem> problems1 = problems.stream().filter(problem1 -> problem1.getDay()==sub.getDay()).collect(Collectors.toList());
            Problem problem = problems1.get(0);
            if(sub.getDay() <=76){
            email.setSubject("Blind 75 Problem: Problem #" +problem.getDay()+" ["+problem.getType()+"]");
            email.setRecipient(sub.getEmail());
            String html = """
                    <!DOCTYPE html>
                    <html lang="en">
                    <head>
                    <meta charset="UTF-8">
                    <meta name="viewport" content="width=device-width, initial-scale=1.0">
                    <title>Email Template</title>
                    <style>
                      body {
                        font-family: Arial, "Helvetica Neue", Helvetica, sans-serif;
                        margin: 0;
                        padding: 0;
                        background-color: #03060d;
                      }
                      .container {
                        max-width: 600px;
                        margin: 0 auto;
                        padding: 20px;
                        background-color: #ffffff;
                      }
                      .header {
                        text-align: center;
                        margin-bottom: 2px;
                        background-color: #f2f2f2;
                        padding: 1px;
                      }
                      .header img {
                        max-width: 50px;
                        height: auto;
                      }
                      .content {
                        margin-bottom: 20px;
                      }
                      .footer {
                        text-align: center;
                        margin-top: 20px;
                        background-color: #f2f2f2; /* grey background */
                        padding: 10px;
                      }
                      .footer a {
                        color: #999999;
                        text-decoration: none;
                      }
                     \s
                    </style>
                    </head>
                    <body>
                      <div class="container">
                        <div class="header">
                          <a  href="www.blindseventyfive.com" > <img src="https://cdn1.iconfinder.com/data/icons/round-icons-vol-2/512/Code_javascript_development-512.png" > </a>
                        </div>
                        <div class="content">
                    	  <p> Good Morning! Here's your leetcode problem for today <p>
                    	
                    	<p>"""+ problem.getProblemDescription().replace("\r\n","<br>").replaceAll("\\^(100000|\\d{1,5})\\b","<sup>$1</sup>")+
                        """
                         \n <a href="+""" + problem.getLink()+ """ 
                        " target="_blank">click here</a> to submit your solution on LeetCode.</p>
                                        
                        <div class="footer">
                         <p><a href="http://www.blindseventyfive.com" target="_blank">www.blindseventyfive.com</a> &nbsp; &nbsp; &nbsp;<a href="#">Unsubscribe</a></p>
                        </div>
                      </div>
                    </body>
                    </html>""";
            email.setBody(html);
            emails.add(email);
        }}

        if(!emails.isEmpty()){
           EmailResponse emailResponse =  emailClient.sendMails(emails);
            for (EmailStructure email: emails //TODO: Change to emailResponse
                 ) {
                if(email.getStatus().equals("sent"));
                {
                    Subscriber sub = subscriberService.findByEmail(email.getRecipient().replace("[","").replace("]",""));
                    if(sub.getDay() <=75){
                        sub.setDay(sub.getDay()+1);
                    }
                    Subscriber subscriber = customSubscriberService.updateSubscriberInfo(sub);
                    System.out.println("Incremented day"+subscriber);
                }
            }
            return  emailResponse;
        }
        return null;
    }
}

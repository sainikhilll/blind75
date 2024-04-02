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

    public List<EmailStructure> triggerEmails(){
        List<Problem> problems = problemService.getAllProblems();
        List<Subscriber> subscribers = subscriberService.getAllSubscribers();
        List<EmailStructure> emails = new ArrayList<>();

        for (Subscriber sub: subscribers) {
            EmailStructure email = new EmailStructure();
          //  int day = subscribers.stream().filter(subscriber -> problem.getDay() ==  )
            List<Problem> problems1 = problems.stream().filter(problem1 -> problem1.getDay()==sub.getDay()).collect(Collectors.toList());
            Problem problem = problems1.get(0);
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
                        background-color: #f4f4f4;
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
                          <a  href="www.blindseventyfive.com" > <img src="testing.svg" alt="Blind 75 Challenge" > </a>
                        </div>
                        <div class="content">
                    	  <p> Good Morning! Here's your leetcode problem for today <p>
                          <p>Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.<br><br>You may assume that each input would have exactly one solution, and you may not use the same element twice.<br><br>You can return the answer in any order.<br><br> <br><br>Example 1:<br><br>Input: nums = [2,7,11,15], target = 9<br>Output: [0,1]<br>Explanation: Because nums[0] + nums[1] == 9, we return [0, 1].<br>Example 2:<br><br>Input: nums = [3,2,4], target = 6<br>Output: [1,2]<br>Example 3:<br><br>Input: nums = [3,3], target = 6<br>Output: [0,1]<br> <br><br>Constraints:<br><br>2 <= nums.length <= 10<sup>4</sup><br>-10<sup>9</sup> <= nums[i] <= 10<sup>9</sup><br>-10<sup>9</sup> <= target <= 10<sup>9</sup><br>Only one valid answer exists.<br> <br><br>Follow-up: Can you come up with an algorithm that is less than O(n2) time complexity?</p>
                        </div>
                    	
                    	<p>"""+ problem.getProblemDescription().replace("\r\n","<br>").replaceAll("\\^(100000|\\d{1,5})\\b","<sup>$1</sup>")+
                        """
                         <a href="https://leetcode.com/" target="_blank">click here</a> to submit your solution on LeetCode.</p>
                                        
                        <div class="footer">
                         <p><a href="http://www.blindseventyfive.com" target="_blank">www.blindseventyfive.com</a> &nbsp; &nbsp; &nbsp;<a href="#">Unsubscribe</a></p>
                        </div>
                      </div>
                    </body>
                    </html>""";
            email.setBody(html);
            emails.add(email);
        }

        if(!emails.isEmpty()){
            return  emails;
        }
        return null;
    }
}

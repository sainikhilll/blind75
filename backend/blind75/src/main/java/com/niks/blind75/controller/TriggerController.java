package com.niks.blind75.controller;

import com.niks.blind75.model.EmailResponse;
import com.niks.blind75.service.TriggerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/trigger")
public class TriggerController {

    @Autowired
    TriggerService triggerService;

    @GetMapping
    public EmailResponse prepareEmails(){
        EmailResponse emails  =  triggerService.triggerEmails();
        return emails;
    }
}

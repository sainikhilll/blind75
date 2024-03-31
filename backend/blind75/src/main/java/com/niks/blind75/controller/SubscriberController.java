package com.niks.blind75.controller;

import com.niks.blind75.model.Subscriber;
import com.niks.blind75.service.SubscriberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subscriber")
public class SubscriberController {
    @Autowired
    SubscriberService subscriberService;
    @PostMapping
    public Subscriber register(@RequestBody Subscriber subscriber){
        Subscriber sub = subscriberService.addSubscriber(subscriber);
        return sub;
    }

    @GetMapping("/getAll")
    public List<Subscriber> getAllSubscribers(){
        List<Subscriber> subscribers = subscriberService.getAllSubscribers();
        return subscribers;
    }

    @GetMapping("/unsubscribe")
    public Subscriber unsubscribe(@RequestParam String mail){
        Subscriber subscriber = subscriberService.unsubscribe(mail);
        return  subscriber;
    }
}

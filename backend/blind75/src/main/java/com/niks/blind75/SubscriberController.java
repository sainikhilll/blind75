package com.niks.blind75;

import com.niks.blind75.model.Subscriber;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/subscriber")
public class SubscriberController {
    @PostMapping
    public static String register(@RequestBody Subscriber subscriber){
        //TODO : Add code to insert subscriber record
        return "added";
    }
}

package com.niks.blind75;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mail")
public class MailController {

    @Autowired
    private EmailClient emailClient;

//    @PostMapping("/send")
//    public ResponseEntity<> sendMail(@RequestBody String to){
//        /* emailClient. */
//        return new ResponseEntity<>(HttpStatus.CREATED);
//    }
}

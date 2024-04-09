package com.niks.blind75.scheduler;

import com.niks.blind75.service.TriggerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.text.SimpleDateFormat;

@EnableScheduling
@Configuration
public class Scheduler {

//    @Autowired
//    TriggerService triggerService;
//    @Scheduled(cron = "* * * * * *")
//    public void everyMinuteJob(){
//        System.out.println("Hello" + java.time.LocalDate.now());
//    }
//
//    @Scheduled(cron = "0 0 6 * * *")
//    public void mailScheduler(){
//        System.out.println("Before:");
//        triggerService.triggerEmails();
//        System.out.println(("Done"));
//    }
}

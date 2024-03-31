package com.niks.blind75.service;

import com.niks.blind75.model.Subscriber;
import com.niks.blind75.repository.SubscriberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class SubscriberService {


    @Autowired
    SubscriberRepository subscriberRepository;

    public List<Subscriber> getAllSubscribers(){
        List<Subscriber> subscribers = subscriberRepository.findAll();
        return subscribers;
    }

    public Subscriber addSubscriber(Subscriber subscriber){
        subscriber.setDay(1);
        subscriber.setSubscriptionStatus("subscribed");
        Subscriber sub = subscriberRepository.save(subscriber);
        return sub;
    }

    public Subscriber updateSubscriber(Subscriber subscriber){

        Subscriber sub = subscriberRepository.findByEmail(subscriber.getEmail());
        if(sub != null){
        Subscriber updtatedSub = subscriberRepository.updateSubscriber(sub.getName(),sub.getEmail(),sub.getSubscriptionStatus());
        return updtatedSub;
        }
        else {
            //TODO
            return sub;
        }

    }
    public Subscriber unsubscribe(String email){
        Subscriber subscriber = subscriberRepository.findByEmail(email);
        subscriber.setSubscriptionStatus("unsubscribed");
        Subscriber sub =  subscriberRepository.updateSubscriber(subscriber.getName(),subscriber.getEmail(),subscriber.getSubscriptionStatus());
        return sub;
    }
}

package com.niks.blind75.service;

import com.mongodb.client.result.UpdateResult;
import com.niks.blind75.model.Subscriber;
import com.niks.blind75.repository.CustomSubscriberRepository;
import com.niks.blind75.repository.SubscriberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class SubscriberService {


    @Autowired
    SubscriberRepository subscriberRepository;

    @Autowired
    CustomSubscriberRepository customSubscriberRepository;

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
    
    public Subscriber unsubscribe(String email){
        Subscriber subscriber = subscriberRepository.findByEmail(email);
        subscriber.setSubscriptionStatus("unsubscribed");
        Subscriber sub = customSubscriberRepository.updateSubscriberInfo(subscriber);
        return sub;
    }
    
    public List<Subscriber>
    dayIncrement(List<Subscriber> subscribers){
        List<Subscriber> updatedSubscribers = new ArrayList<>();
        for (Subscriber subscriber: subscribers
             ) {
            subscriber.setDay(subscriber.getDay()+1);
            Subscriber sub = customSubscriberRepository.updateSubscriberInfo(subscriber);
            updatedSubscribers.add(sub);
            
        }
        return updatedSubscribers;
    }

   public  Subscriber findByEmail(String email){
        Subscriber sub = subscriberRepository.findByEmail(email);
        return sub;
   }
}

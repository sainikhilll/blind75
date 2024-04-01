package com.niks.blind75.service;

import com.mongodb.client.result.UpdateResult;
import com.niks.blind75.model.Subscriber;
import com.niks.blind75.repository.CustomSubscriberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

@Service
public class CustomSubscriberService implements CustomSubscriberRepository {
    @Autowired
    MongoTemplate mongoTemplate;
    @Override
    public Subscriber updateSubscriberInfo(Subscriber subscriber) {
        Query query = new org.springframework.data.mongodb.core.query.Query(Criteria.where("email").is(subscriber.getEmail()));
        Update update = new Update();
        update.set("name",subscriber.getName());
        update.set("subscriptionStatus",subscriber.getSubscriptionStatus());
        update.set("day",subscriber.getDay());
        UpdateResult result = mongoTemplate.updateFirst(query,update,Subscriber.class);
        if(result == null){
            System.out.println("No documents updated");
            return null;
        }else {
            return subscriber;
        }
    }
}

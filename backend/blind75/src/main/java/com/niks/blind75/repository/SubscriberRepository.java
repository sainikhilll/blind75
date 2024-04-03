package com.niks.blind75.repository;

import com.niks.blind75.model.Subscriber;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.Update;

public interface SubscriberRepository  extends MongoRepository<Subscriber,String> {
    public Subscriber findByEmail(String email);


}

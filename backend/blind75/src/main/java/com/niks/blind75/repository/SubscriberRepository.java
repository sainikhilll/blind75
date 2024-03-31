package com.niks.blind75.repository;

import com.niks.blind75.model.Subscriber;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface SubscriberRepository  extends MongoRepository<Subscriber,String> {
    public Subscriber findByEmail(String email);

    @Query("{'email' : ?1}, {'$set': {'name' : ?0, 'subscriptionStatus' : ?2}}")
    public Subscriber updateSubscriber(String name, String email, String subscriptionStatus);
}

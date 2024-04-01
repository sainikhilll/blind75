package com.niks.blind75.repository;

import com.niks.blind75.model.Subscriber;
import com.niks.blind75.service.SubscriberService;

public interface CustomSubscriberRepository {

    Subscriber updateSubscriberInfo(Subscriber subscriber);
}

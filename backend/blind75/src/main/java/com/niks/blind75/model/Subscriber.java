package com.niks.blind75.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Data
@Document(collection = "subscriber")
public class Subscriber {
    private String name;
    private String email;
    private int day;
    private String subscriptionStatus;
}

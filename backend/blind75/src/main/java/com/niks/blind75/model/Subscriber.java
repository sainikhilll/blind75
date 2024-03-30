package com.niks.blind75.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class Subscriber {
    private String name;
    private String email;
    private int day;
}

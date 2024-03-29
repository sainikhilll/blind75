package com.niks.blind75.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class EmailStructure {
    private String recipient;
    private String subject;
    private String body;
    private String status;
}

package com.niks.blind75.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Data
public class EmailResponse {
    private List<EmailStructure> emails;
}

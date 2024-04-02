package com.niks.blind75.model;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.security.Signature;

@Data
@Getter
@Setter
@Document(collection = "poblem")
@NoArgsConstructor
@AllArgsConstructor
public class Problem {
    private String type;
    private String difficulty;
    private String name;
    private String problemDescription;
    private String link;
    private int day;
}

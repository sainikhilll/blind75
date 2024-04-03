package com.niks.blind75.repository;

import com.niks.blind75.model.Problem;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProblemRepository extends MongoRepository<Problem,String> {
}

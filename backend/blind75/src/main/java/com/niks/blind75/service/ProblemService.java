package com.niks.blind75.service;

import com.niks.blind75.model.Problem;
import com.niks.blind75.repository.ProblemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProblemService {
    @Autowired
    ProblemRepository problemRepository;

    public List<Problem> getAllProblems(){
        List<Problem> problems = problemRepository.findAll();
        return problems;
    }
}

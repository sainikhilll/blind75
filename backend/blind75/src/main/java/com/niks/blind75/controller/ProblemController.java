package com.niks.blind75.controller;

import com.niks.blind75.model.Problem;
import com.niks.blind75.repository.ProblemRepository;
import com.niks.blind75.service.ProblemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/problem")
public class ProblemController {

    @Autowired
    ProblemService problemService;

    @GetMapping
    public List<Problem> getProblems(){
        List<Problem> problems = problemService.getAllProblems();
        return problems;
    }
}

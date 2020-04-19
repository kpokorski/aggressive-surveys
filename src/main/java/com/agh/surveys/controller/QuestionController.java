package com.agh.surveys.controller;

import com.agh.surveys.model.poll.question.Question;
import com.agh.surveys.model.poll.question.type.QuestionDetails;
import com.agh.surveys.service.question.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("polls")
class QuestionController {

    @Autowired
    QuestionService questionService;

    // Aggregate root

    @GetMapping("/{poolId}/questions")
    List<Question> all(@PathVariable Long poolId) {
        return questionService.findAll(poolId);
    }

    @PostMapping("/{poolId}/questions")
    Question addQuestion(@PathVariable Long poolId, @RequestBody QuestionDetails questionDetails) {
        return questionService.addQuestion(poolId, questionDetails);
    }

    @GetMapping("/{poolId}/questions/{questionId}")
    Question findQuestion(@PathVariable Long poolId, @PathVariable Long questionId) {
        return questionService.getQuestion(poolId, questionId);
    }

    @DeleteMapping("/{poolId}/questions/{questionId}")
    void deleteQuestion(@PathVariable Long poolId, @PathVariable Long questionId) {
        questionService.deleteQuestion(poolId, questionId);
    }
}

package org.skypro.coursework2.controller;


import org.skypro.coursework2.model.Question;
import org.skypro.coursework2.service.QuestionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/exam/java")
public class MathQuestionController {

    private final QuestionService questionService;

    public MathQuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping("/add")
    public Question addQuestion(@RequestParam String question, @RequestParam String answer) {
        return questionService.add(question, answer);
    }

    @GetMapping("/remove")
    public Question removeQuestion(@RequestParam String question, @RequestParam String answer) {
        Question q = new Question(question, answer);
        return questionService.remove(q);
    }

    @GetMapping
    public Collection<Question> getAllQuestions() {
        return questionService.getAll();
    }

    @GetMapping("/find")
    public Question findQuestion(@RequestParam String question, @RequestParam String answer) {
        Question q = new Question(question, answer);
        return questionService.find(q);
    }
}

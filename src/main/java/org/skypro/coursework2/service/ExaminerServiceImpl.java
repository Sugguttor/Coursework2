package org.skypro.coursework2.service;

import org.skypro.coursework2.model.Question;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;


@Service
public class ExaminerServiceImpl implements ExaminerService {

    private final QuestionService javaQuestionService;
    private final QuestionService mathQuestionService;

    public ExaminerServiceImpl(QuestionService javaQuestionService, QuestionService mathQuestionService) {
        this.javaQuestionService = javaQuestionService;
        this.mathQuestionService = mathQuestionService;
    }

    @Override
    public Collection<Question> getQuestions(int amount) {
        int totalQuestions = javaQuestionService.getAll().size() + mathQuestionService.getAll().size();
        if (amount <= 0 || amount > totalQuestions) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid amount of questions requested.");
        }

        Set<Question> questions = new HashSet<>();
        Random random = new Random();

        while (questions.size() < amount) {
            Question question;
            if (random.nextBoolean()) {
                question = javaQuestionService.getRandomQuestion();
            } else {
                question = mathQuestionService.getRandomQuestion();
            }
            if(question != null){ // avoid nullpointerException and duplicates in set
                questions.add(question);
            }
        }
        return questions;
    }
}


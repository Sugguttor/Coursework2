package org.skypro.coursework2.service;

import org.skypro.coursework2.model.Question;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;


@Service
    public class ExaminerServiceImpl implements ExaminerService {

        private final QuestionService questionService;

        public ExaminerServiceImpl(QuestionService questionService) {
            this.questionService = questionService;
        }

        @Override
        public Collection<Question> getQuestions(int amount) {
            if (amount <= 0 || amount > questionService.getAll().size()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid amount of questions requested.");
            }

            Set<Question> questions = new HashSet<>();
            while (questions.size() < amount) {
                questions.add(questionService.getRandomQuestion());
            }
            return questions;
        }
    }


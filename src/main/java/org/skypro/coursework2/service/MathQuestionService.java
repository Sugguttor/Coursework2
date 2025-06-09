package org.skypro.coursework2.service;

import org.skypro.coursework2.model.Question;
import org.skypro.coursework2.repository.QuestionRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

@Service
public class MathQuestionService implements QuestionService {

    private final QuestionRepository questionRepository;
    private final Random random = new Random();

    public MathQuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
        generateQuestions();
    }

    private void generateQuestions() {
        for (int i = 0; i < 10; i++) {
            int num1 = random.nextInt(100);
            int num2 = random.nextInt(100);
            String operation = "+-*/".charAt(random.nextInt(4)) + "";
            String question = num1 + " " + operation + " " + num2 + " = ?";
            int answer;
            switch (operation) {
                case "+":
                    answer = num1 + num2;
                    break;
                case "-":
                    answer = num1 - num2;
                    break;
                case "*":
                    answer = num1 * num2;
                    break;
                case "/":
                    answer = num2 == 0 ? 0 : num1 / num2;
                    break;
                default:
                    answer = 0;
            }
            questionRepository.add(new Question(question, String.valueOf(answer)));
        }
    }

    @Override
    public Question add(String question, String answer) {
        Question newQuestion = new Question(question, answer);
        return add(newQuestion);
    }

    @Override
    public Question add(Question question) {
        questionRepository.add(question);
        return question;
    }

    @Override
    public Question remove(Question question) {
        questionRepository.remove(question);
        return question;
    }

    @Override
    public Question find(Question question) {
        return questionRepository.getAll().stream()
                .filter(q -> q.equals(question))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Collection<Question> getAll() {
        return questionRepository.getAll();
    }

    @Override
    public Question getRandomQuestion() {
        Collection<Question> allQuestions = questionRepository.getAll();
        if (allQuestions.isEmpty()) {
            return null;
        }
        int randomIndex = random.nextInt(allQuestions.size());
        return new ArrayList<>(allQuestions).get(randomIndex);
    }
}

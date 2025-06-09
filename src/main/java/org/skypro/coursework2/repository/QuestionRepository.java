package org.skypro.coursework2.repository;

import org.skypro.coursework2.model.Question;

import java.util.Collection;

public interface QuestionRepository {
    Question add(Question question);
    Question remove(Question question);
    Collection<Question> getAll();
}

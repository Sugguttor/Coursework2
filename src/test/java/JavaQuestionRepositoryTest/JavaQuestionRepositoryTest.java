package JavaQuestionRepositoryTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.skypro.coursework2.model.Question;
import org.skypro.coursework2.repository.JavaQuestionRepository;
import org.skypro.coursework2.repository.QuestionRepository;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

public class JavaQuestionRepositoryTest {

    private QuestionRepository questionRepository;
    private Question question1;
    private Question question2;

    @BeforeEach
    void setUp() {
        questionRepository = new JavaQuestionRepository();
        question1 = new Question("Question 1", "Answer 1");
        question2 = new Question("Question 2", "Answer 2");
    }

    @Test
    void add_shouldAddQuestionToRepository() {
        questionRepository.add(question1);
        Collection<Question> allQuestions = questionRepository.getAll();

        assertTrue(allQuestions.contains(question1));
    }

    @Test
    void remove_shouldRemoveQuestionFromRepository() {
        questionRepository.add(question1);
        questionRepository.remove(question1);

        Collection<Question> allQuestions = questionRepository.getAll();

        assertFalse(allQuestions.contains(question1));
    }

    @Test
    void getAll_shouldReturnAllQuestionsFromRepository() {
        questionRepository.add(question1);
        questionRepository.add(question2);

        Collection<Question> allQuestions = questionRepository.getAll();

        assertEquals(2, allQuestions.size());
        assertTrue(allQuestions.contains(question1));
        assertTrue(allQuestions.contains(question2));
    }
}

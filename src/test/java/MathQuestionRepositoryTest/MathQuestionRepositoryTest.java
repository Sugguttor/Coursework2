package MathQuestionRepositoryTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.skypro.coursework2.model.Question;
import org.skypro.coursework2.repository.MathQuestionRepository;
import org.skypro.coursework2.repository.QuestionRepository;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

public class MathQuestionRepositoryTest {

    private QuestionRepository questionRepository;
    private Question question1;
    private Question question2;

    @BeforeEach
    void setUp() {
        questionRepository = new MathQuestionRepository();
        question1 = new Question("2 + 2 = ?", "4");
        question2 = new Question("5 * 3 = ?", "15");
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

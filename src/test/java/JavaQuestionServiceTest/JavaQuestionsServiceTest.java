package JavaQuestionServiceTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.skypro.coursework2.model.Question;
import org.skypro.coursework2.service.JavaQuestionService;
import org.skypro.coursework2.service.QuestionService;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

public class JavaQuestionsServiceTest {
    private QuestionService questionService;

    @BeforeEach
    void setUp() {
        questionService = new JavaQuestionService();
    }

    @Test
    void add_shouldAddQuestion() {
        Question question = new Question("Question 1", "Answer 1");

        questionService.add(question);
        Collection<Question> allQuestions = questionService.getAll();

        assertTrue(allQuestions.contains(question));
    }

    @Test
    void remove_shouldRemoveQuestion() {
        Question question = new Question("Question 1", "Answer 1");

        questionService.add(question);
        questionService.remove(question);
        Collection<Question> allQuestions = questionService.getAll();

        assertFalse(allQuestions.contains(question));
    }

    @Test
    void getAll_shouldReturnAllQuestions() {
        Question question1 = new Question("Question 1", "Answer 1");
        Question question2 = new Question("Question 2", "Answer 2");

        questionService.add(question1);
        questionService.add(question2);
        Collection<Question> allQuestions = questionService.getAll();

        assertEquals(2, allQuestions.size());
        assertTrue(allQuestions.contains(question1));
        assertTrue(allQuestions.contains(question2));
    }

    @Test
    void getRandomQuestion_shouldReturnRandomQuestion() {
        Question question1 = new Question("Question 1", "Answer 1");
        Question question2 = new Question("Question 2", "Answer 2");

        questionService.add(question1);
        questionService.add(question2);
        Question randomQuestion = questionService.getRandomQuestion();

        assertNotNull(randomQuestion);
        assertTrue(questionService.getAll().contains(randomQuestion));
    }
}

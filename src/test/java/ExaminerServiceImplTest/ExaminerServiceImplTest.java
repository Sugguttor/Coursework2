package ExaminerServiceImplTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.coursework2.model.Question;
import org.skypro.coursework2.service.ExaminerServiceImpl;
import org.skypro.coursework2.service.QuestionService;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ExaminerServiceImplTest {

    @Mock
    private QuestionService questionService;

    @InjectMocks
    private ExaminerServiceImpl examinerService;

    private final Set<Question> questions = new HashSet<>();

    @BeforeEach
    void setUp() {
        questions.add(new Question("Question 1", "Answer 1"));
        questions.add(new Question("Question 2", "Answer 2"));
        questions.add(new Question("Question 3", "Answer 3"));

        when(questionService.getAll()).thenReturn(questions);
        when(questionService.getRandomQuestion()).thenReturn(questions.iterator().next()); // Always return the first question for simplicity
    }

    @Test
    void getQuestions_shouldReturnCorrectNumberOfUniqueQuestions() {
        int amount = 2;
        Collection<Question> examQuestions = examinerService.getQuestions(amount);
        assertEquals(amount, examQuestions.size());
    }

    @Test
    void getQuestions_shouldThrowBadRequestExceptionWhenAmountIsInvalid() {
        int amount = 4; // More than available questions
        assertThrows(ResponseStatusException.class, () -> examinerService.getQuestions(amount));
    }

    @Test
    void getQuestions_shouldReturnEmptySetWhenNoQuestionsAvailable() {
        when(questionService.getAll()).thenReturn(new HashSet<>());
        int amount = 1;
        assertThrows(ResponseStatusException.class, () -> examinerService.getQuestions(amount));
    }
}

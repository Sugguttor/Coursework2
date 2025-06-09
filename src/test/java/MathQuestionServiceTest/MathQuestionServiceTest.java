package MathQuestionServiceTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.coursework2.model.Question;
import org.skypro.coursework2.repository.QuestionRepository;
import org.skypro.coursework2.service.MathQuestionService;

import java.util.Collection;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class MathQuestionServiceTest {
    @Mock
    private QuestionRepository questionRepository;

    @InjectMocks
    private MathQuestionService mathQuestionService;

    private Question question1;
    private Question question2;

    @BeforeEach
    void setUp() {
        question1 = new Question("2 + 2 = ?", "4");
        question2 = new Question("5 * 3 = ?", "15");
    }

    @Test
    void add_shouldAddQuestionToRepository() {

        when(questionRepository.add(question1)).thenReturn(question1);

        Question addedQuestion = mathQuestionService.add(question1);

        assertEquals(question1, addedQuestion);
        verify(questionRepository, times(1)).add(question1);
    }

    @Test
    void remove_shouldRemoveQuestionFromRepository() {
        doNothing().when(questionRepository).remove(question1);

        mathQuestionService.remove(question1);

        verify(questionRepository, times(1)).remove(question1);
    }

    @Test
    void getAll_shouldReturnAllQuestionsFromRepository() {
        HashSet<Question> questions = new HashSet<>();
        questions.add(question1);
        questions.add(question2);
        when(questionRepository.getAll()).thenReturn(questions);

        Collection<Question> allQuestions = mathQuestionService.getAll();

        assertEquals(2, allQuestions.size());
        assertTrue(allQuestions.contains(question1));
        assertTrue(allQuestions.contains(question2));
        verify(questionRepository, times(1)).getAll();
    }

    @Test
    void getRandomQuestion_shouldReturnRandomQuestionFromRepository() {
        HashSet<Question> questions = new HashSet<>();
        questions.add(question1);
        questions.add(question2);
        when(questionRepository.getAll()).thenReturn(questions);

        Question randomQuestion = mathQuestionService.getRandomQuestion();

        assertNotNull(randomQuestion);
        assertTrue(questions.contains(randomQuestion));
        verify(questionRepository, times(1)).getAll();
    }

    @Test
    void getRandomQuestion_shouldReturnNullWhenRepositoryIsEmpty() {
        when(questionRepository.getAll()).thenReturn(new HashSet<>());

        Question randomQuestion = mathQuestionService.getRandomQuestion();

        assertNull(randomQuestion);
        verify(questionRepository, times(1)).getAll();
    }
}

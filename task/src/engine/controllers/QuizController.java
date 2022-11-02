package engine.controllers;

import engine.dtos.requests.AnswerRequest;
import engine.dtos.requests.QuizRequest;
import engine.dtos.requests.UserRegistrationRequest;
import engine.dtos.responses.AnswerResponse;
import engine.dtos.responses.CompletedQuizResponse;
import engine.dtos.responses.QuizResponse;
import engine.services.QuizService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class QuizController {

    private final QuizService quizService;

    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @GetMapping("/quizzes/{id}")
    public QuizResponse getQuiz(@PathVariable long id) {
        return quizService.getQuizById(id);
    }

    @GetMapping("/quizzes")
    public Page<QuizResponse> getAllQuizzes(@RequestParam(defaultValue = "0") Integer page) {
        return quizService.getAllQuizzes(page);
    }

    @PostMapping("/quizzes")
    public QuizResponse addQuiz(@Valid @RequestBody QuizRequest request, Authentication authentication) {
        String name = authentication.getName();
        return quizService.addQuiz(request, name);
    }

    @PostMapping("/quizzes/{id}/solve")
    public AnswerResponse answerQuiz(@RequestBody AnswerRequest answer, @PathVariable long id,
                                     Authentication authentication) {
        String name = authentication.getName();
        return quizService.checkQuizAnswer(id, answer, name);
    }

    @PostMapping("/register")
    public void register(@Valid @RequestBody UserRegistrationRequest userRequest) {
        quizService.registerUser(userRequest);
    }

    @DeleteMapping("/quizzes/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteQuiz(@PathVariable long id, Authentication authentication) {
        String name = authentication.getName();
        quizService.deleteQuiz(id, name);
    }

    @GetMapping("/quizzes/completed")
    public Page<CompletedQuizResponse> getAllCompletedQuizzes(@RequestParam(defaultValue = "0") Integer page,
                                                     Authentication authentication) {
        String name = authentication.getName();
        return quizService.getAllUsersCompleted(page, name);
    }
}

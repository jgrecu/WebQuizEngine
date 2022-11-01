package engine.controllers;

import engine.dtos.requests.AnswerRequest;
import engine.dtos.responses.AnswerResponse;
import engine.dtos.responses.QuizResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class QuizController {

    @GetMapping("/quiz")
    public QuizResponse getQuiz() {
        return new QuizResponse("The Java Logo", "What is depicted on the Java logo?",
                List.of("Robot","Tea leaf","Cup of coffee","Bug"));
    }

    @PostMapping("/quiz")
    public AnswerResponse answerQuiz(@RequestParam(name = "answer") int answer) {
        if (answer == 2) {
            return new AnswerResponse(true);
        }
        return new AnswerResponse(false);
    }
}

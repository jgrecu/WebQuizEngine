package engine.dtos.requests;

import org.apache.catalina.LifecycleState;

import java.util.List;

public class AnswerRequest {
    private List<Integer> answer;

    public AnswerRequest() {
    }

    public AnswerRequest(List<Integer> answer) {
        this.answer = answer;
    }

    public List<Integer> getAnswer() {
        return answer;
    }

    public void setAnswer(List<Integer> answer) {
        this.answer = answer;
    }

    @Override
    public String toString() {
        return "AnswerRequest{" +
                "answer=" + answer +
                '}';
    }
}

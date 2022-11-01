package engine.dtos.requests;

public class AnswerRequest {
    private int answer;

    public AnswerRequest() {
    }

    public AnswerRequest(int answer) {
        this.answer = answer;
    }

    public int getAnswer() {
        return answer;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }

    @Override
    public String toString() {
        return "AnswerRequest{" +
                "answer=" + answer +
                '}';
    }
}

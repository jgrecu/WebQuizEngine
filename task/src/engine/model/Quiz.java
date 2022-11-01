package engine.model;

import engine.dtos.requests.QuizRequest;

import java.util.List;

public class Quiz {
    private int id;
    private String title;
    private String text;
    private List<String> options;
    private List<Integer> answer;

    public Quiz() {
    }

    public Quiz(QuizRequest request) {
        this.title = request.getTitle();
        this.text = request.getText();
        this.options = request.getOptions();
        this.answer = request.getAnswer();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    public List<Integer> getAnswer() {
        return answer;
    }

    public void setAnswer(List<Integer> answer) {
        this.answer = answer;
    }
}

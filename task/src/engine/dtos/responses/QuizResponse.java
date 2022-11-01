package engine.dtos.responses;

import java.util.ArrayList;
import java.util.List;

public class QuizResponse {
    private String title;
    private String text;
    private List<String> options;

    public QuizResponse() {
        this.options = new ArrayList<>();
    }

    public QuizResponse(String title, String text, List<String> options) {
        this.title = title;
        this.text = text;
        this.options = options;
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
}

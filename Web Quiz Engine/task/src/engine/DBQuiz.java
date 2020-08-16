package engine;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@SequenceGenerator(name="seq", initialValue=1, allocationSize=1000)
public class DBQuiz {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq")
    //@GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "title is mandatory")
    private String title;
    @NotBlank(message = "text is mandatory")
    private String text;
    @Size(min=2) @NotNull
    private String[] options;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private int[] answer = new int[0];

    public int[] getAnswer() {
        return answer;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // getters and setters
    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAnswer(int[] answer) {
        this.answer = answer;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setOptions(String[] options) {
        this.options = options;
    }

    public Long getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public String[] getOptions() {
        return options;
    }



}

package engine;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.security.Principal;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import engine.DBService;

@SpringBootApplication
public class WebQuizEngine {

    public static void main(String[] args) {
        SpringApplication.run(WebQuizEngine.class, args);

    }

}

class Answer {
    private int[] answer;

    public Answer() {}

    public Answer(int[] answer) {
        this.answer = answer.clone();
    }

    public int[] getAnswer() {
        return answer;
    }

}

class Quiz {

    private int id;
    @NotBlank(message = "title is mandatory")
    private String title;
    @NotBlank(message = "text is mandatory")
    private String text;
    @Size(min=2) @NotNull
    private String[] options;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private int[] answer = new int[0];

    /*@JsonCreator
    public Quiz(@JsonProperty("title") String title,
             @JsonProperty("text") String text,
             @JsonProperty("options") String[] options,
             @JsonProperty("answer") int answer) {
        this.title = title;
        this.text = text;
        this.options = options;
        this.answer = answer;

    }*/
    public int[] getAnswer() {
        return answer;
    }

    public void setId(int id) {
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

    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public String[] getOptions() {
        return options;
    }

    public String toString() {
        String result = "";
        result += "{\"id\":" + this.id + ",\n";
        result += "\"title\":" + "\"" + this.title + "\",\n";
        result += "\"text\":" + "\"" + this.text + "\",\n";
        result += "\"options\":" + optToString() + "\n" + "}";
        return result;
    }

    public String optToString() {
        String res = "[";

        for (String opt : options) {
            res += "\"" + opt + "\",";
        }
        res = res.substring(0, res.length() -1);
        res += "]";
        return res;
    }
}

@RestController
class QuizManager {
    private List<Quiz> quizzes = new ArrayList<>();
    @Autowired
    private DBServiceImpl dbService;


    //public QuizManager() {}

    /*@PostMapping(path = "/api/quizzes/{id}/solve", consumes = "application/json")
    public String solver(@PathVariable int id, @RequestBody  Answer answer){
        try {
            if (Arrays.equals(answer.getAnswer(), quizzes.get(id - 1).getAnswer())) {
                return "{\"success\":true,\"feedback\":\"Congratulations, you're right!\"}";
            } else {
                return "{\"success\":false,\"feedback\":\"Wrong answer! Please, try again.\"}";
            }
        } catch (Exception exc) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Quiz Not Found", exc);
        }
    }*/

    @PostMapping(path = "/api/quizzes/{id}/solve", consumes = "application/json")
    public String solver(@PathVariable long id, @RequestBody  Answer answer){
        try {
            DBQuiz dbQuiz = dbService.getDBQuizById(id);
            if (Arrays.equals(answer.getAnswer(), dbQuiz.getAnswer())) {
                return "{\"success\":true,\"feedback\":\"Congratulations, you're right!\"}";
            } else {
                return "{\"success\":false,\"feedback\":\"Wrong answer! Please, try again.\"}";
            }
        } catch (Exception exc) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Quiz Not Found", exc);
        }
    }

    /*@PostMapping(value = "/api/quizzes")
    public ResponseEntity<DBQuiz> saveDBQuiz(
           @Valid @RequestBody final DBQuiz dbquiz) {
        DBQuiz savedDBQuiz = dbService.saveDBQuiz(dbquiz);
        return new ResponseEntity<>(savedDBQuiz, HttpStatus.OK);
    }*/


    @PostMapping(value = "/api/quizzes")
    public ResponseEntity<DBQuiz> saveDBQuiz(
            @Valid @RequestBody final DBQuiz dbquiz, @Autowired Principal principal) {
        try {
           // System.out.println(principal.getName());
            DBQuiz savedDBQuiz = dbService.saveDBQuiz(dbquiz, principal.getName());
            return new ResponseEntity<>(savedDBQuiz, HttpStatus.OK);
        } catch (Exception exc) {
            System.out.println(exc);
            throw exc;
        }

    }

    /*@PostMapping(value = "/api/quizzes", consumes = "application/json")
    public Quiz addQuiz(@Valid @RequestBody Quiz quiz) {
        quiz.setId(quizzes.size() + 1);
        quizzes.add(quiz);
        return quiz;

    }*/

    /*@GetMapping(path = "/api/quizzes")
    public List<Quiz> getAll() {
        return quizzes;
    }*/

    /*@GetMapping(path = "/api/quizzes")
    public ResponseEntity<List<DBQuiz>> getDBQuiz() {
        List<DBQuiz> quizList = dbService.getAllDBQuiz();
        return new ResponseEntity<>(quizList, HttpStatus.OK);
    }*/

    @GetMapping(path = "/api/quizzes", produces = "application/json")
    public ResponseEntity<Page<DBQuiz>> getDBQuiz(Pageable pageable) {
        Page<DBQuiz> quizList = dbService.getAllDBQuizPage(pageable);
        return new ResponseEntity<>(quizList, HttpStatus.OK);
    }


    /*@GetMapping(path = "/api/quizzes/{id}")
    public Quiz getQuiz(@PathVariable int id){
        try {
            return quizzes.get(id - 1);
        } catch (Exception exc) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Quiz Not Found", exc);
        }
    }*/

    @GetMapping(path = "/api/quizzes/{id}")
    public ResponseEntity<DBQuiz> getDBQuizById(
            @PathVariable("id") final Long id) {
        try {
            DBQuiz dbQuiz = dbService.getDBQuizById(id);
            return new ResponseEntity<>(dbQuiz, HttpStatus.OK);
        } catch (Exception exc) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Quiz Not Found", exc);
        }
    }

    @DeleteMapping(path = "/api/quizzes/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteQuiz(@PathVariable long id,
                           @Autowired Principal principal) {
        System.out.print(principal);
        dbService.deleteDBQuizById(id, principal.getName());
    }
    /*public ResponseEntity<DBQuiz> deleteDBQuizById(
            @PathVariable("id") final Long id, @Autowired Principal principal) {
        try {
            dbService.deleteDBQuizById(id, principal.getName());
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception exc) {
            throw new ResponseStatusException(
                    HttpStatus.NO_CONTENT, "Quiz Not Found", exc);
        }
    }*/

}

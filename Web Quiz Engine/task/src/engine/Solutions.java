package engine;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@SequenceGenerator(name="seq", initialValue=1, allocationSize=1000)
public class Solutions {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long id;

    /*@ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;*/
    //@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String user;

    /*@ManyToOne
    @JoinColumn(name = "dbquiz_id", nullable = false)
    private DBQuiz dbquiz;*/
    private long dbquizId;

    @Column(columnDefinition = "TIMESTAMP", nullable = false)
    private LocalDateTime completedAt = LocalDateTime.now();

    /*public static Solutions createSolutions(User user, DBQuiz dbquiz) {
        var solution = new Solutions();
        solution.setUser(user);
        solution.setDBQuiz(dbquiz);
        return solution;
    }*/

    /*public static Solutions createSolutions(String user, long dbquizId) {
        //var solution = new Solutions();
        Solutions solution = new Solutions();
        solution.setUser(user);
        solution.setDBQuiz(dbquizId);
        return solution;
    }*/

    public static Solutions createSolutions(String user, long dbquizId) {
System.out.println(user + " " + dbquizId);
        Solutions solution = new Solutions();
        System.out.println("OK!");
        return solution;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /*public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }*/

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    /*public DBQuiz getDBQuiz() {
        return dbquiz;
    }

    public void setDBQuiz(DBQuiz dbquiz) {
        this.dbquiz = dbquiz;
    }*/

    public long getDBQuiz() {
        return dbquizId;
    }

    public void setDBQuiz(long dbquizId) {
        this.dbquizId = dbquizId;
    }

    public LocalDateTime getCompletedAt() {
        return completedAt;
    }

    public void setCompletedAt(LocalDateTime completedAt) {
        this.completedAt = completedAt;
    }

//    @Override
    public String toString() {
        /*return "Completion{" +
                "id=" + id +
                ", user=" + user +
                ", quiz=" + dbquizId +
                ", completedAt=" + completedAt +
                '}';*/
        return "Completion{" +
                "id=" + dbquizId +
                ", completedAt=" + completedAt +
                '}';

    }
}
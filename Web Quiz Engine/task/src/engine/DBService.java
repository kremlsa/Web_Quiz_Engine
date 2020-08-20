package engine;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DBService {

    List<DBQuiz> getAllDBQuiz();

    DBQuiz getDBQuizById(Long id);

    void deleteDBQuizById(Long id, String username);

    DBQuiz saveDBQuiz(DBQuiz DBQuiz, String username);

    //List<DBQuiz> getDBQuizByNameContaining(String searchString);

    List<DBQuiz> getDBQuizByNameLike(String searchString);

    //public Page<Quiz> getAllDBQuizPage(Pageable pageable);
}
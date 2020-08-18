package engine;

import java.util.List;

public interface DBService {

    List<DBQuiz> getAllDBQuiz();

    DBQuiz getDBQuizById(Long id);

    void deleteDBQuizById(Long id, String username);

    DBQuiz saveDBQuiz(DBQuiz DBQuiz, String username);

    //List<DBQuiz> getDBQuizByNameContaining(String searchString);

    List<DBQuiz> getDBQuizByNameLike(String searchString);
}
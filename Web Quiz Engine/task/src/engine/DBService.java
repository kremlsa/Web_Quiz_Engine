package engine;

import java.util.List;

public interface DBService {

    List<DBQuiz> getAllDBQuiz();

    DBQuiz getDBQuizById(Long id);

    DBQuiz saveDBQuiz(DBQuiz DBQuiz);

    //List<DBQuiz> getDBQuizByNameContaining(String searchString);

    List<DBQuiz> getDBQuizByNameLike(String searchString);
}
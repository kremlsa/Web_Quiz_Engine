package engine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DBServiceImpl implements DBService {

    @Autowired
    private DBRepository DBRepository;

    @Override
    public List<DBQuiz> getAllDBQuiz() {
        return (List<DBQuiz>) DBRepository.findAll();
    }

    @Override
    public DBQuiz getDBQuizById(final Long id) {
        return DBRepository.findById(id).get();
    }

    @Override
    public DBQuiz saveDBQuiz(final DBQuiz DBQuiz) {
        return DBRepository.save(DBQuiz);
    }

   /* @Override
    public List<DBQuiz> getDBQuizByNameContaining(final String searchString) {
        return DBRepository.findByNameContaining(searchString);
    }*/

    @Override
    public List<DBQuiz> getDBQuizByNameLike(final String searchString) {
        return DBRepository.findByNameLike(searchString);
    }
}
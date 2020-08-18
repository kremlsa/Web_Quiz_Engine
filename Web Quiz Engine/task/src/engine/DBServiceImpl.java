package engine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;

@Service
public class DBServiceImpl implements DBService {

    @Autowired
    private DBRepository DBRepository;
    private UserRepository userRepository;

    @Override
    public List<DBQuiz> getAllDBQuiz() {
        return (List<DBQuiz>) DBRepository.findAll();
    }

    @Override
    public void deleteDBQuizById(Long id, String username) {
            /*var quiz = getDBQuizById(id);
            var user = userRepository.findByUsername(username)
                    .orElseThrow(UserNotFoundException::new);*/
            //var quiz = getDBQuizById(id);
            /*var user = userRepository.findByUsername(username)
                .orElseThrow(UserNotFoundException::new);*/
            DBQuiz quiz = getDBQuizById(id);

            if (Objects.equals(quiz.getCreatedBy(), username)) {
                DBRepository.delete(quiz);
            } else {
                throw new NotPermittedException();
            }
            /*if (Objects.equals(quiz.getCreatedBy().getId(), user.getId())) {
                DBRepository.delete(quiz);
            } else {
                throw new NotPermittedException();
            }*/
        }

   /* @Override
    public DBQuiz getDBQuizById(final Long id) {
        return DBRepository.findById(id).get();
    }*/
   @Override
    public DBQuiz getDBQuizById(final Long id) {
       try {
           DBQuiz optionalQuiz = DBRepository.findById(id).get();
           return optionalQuiz;
       } catch (Exception exc) {
           throw new ResponseStatusException(
                   HttpStatus.NOT_FOUND, "Quiz Not Found", exc);
       }
        //return optionalQuiz.orElseThrow(QuizNotFoundException::new);
    }

//    @Override
//    public DBQuiz saveDBQuiz(final DBQuiz DBQuiz) {
//        return DBRepository.save(DBQuiz);
//    }
    @Override
    public DBQuiz saveDBQuiz(DBQuiz dbQuiz, String username)
    {
        /*User user = userRepository.findByUsername(username)
               .orElseThrow(UserNotFoundException::new);*/
        //User nuser = new User();
        //System.out.println(username);

        dbQuiz.setCreatedBy(username);

    return DBRepository.save(dbQuiz);
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
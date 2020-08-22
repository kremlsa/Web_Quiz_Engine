package engine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;
import java.util.Set;

@Service
public class SolService {
    @Autowired
    private SolRepository solRepository;

    public Page<Solutions> findAllCompletedQuizzesAsPage(String username, Pageable pageable) {

        return solRepository.findAllByUserOrderByCompletedAtDesc(username, pageable);
    }

   /* public Page<String> findAllCompletedQuizzesAsPage(String username, Pageable pageable) {

        return solRepository.findAllByUserOrderByCompletedAtDesc(username, pageable);
    }*/

    public void saveSolution(String username, long dbQuizId) {
        //var user = userRepository.findByUsername(username)
        //       .orElseThrow(UserNotFoundException::new);
        //var quiz = getDBQuizById(dbQuizId);
        Solutions sol = new Solutions();
        sol.setUser(username);
        sol.setDBQuiz(dbQuizId);
        System.out.println("beforeSave");
        solRepository.save(sol);
        System.out.println("afterSave");
        //solRepository.save(Solutions.createSolutions(username, dbQuizId));
    }
}
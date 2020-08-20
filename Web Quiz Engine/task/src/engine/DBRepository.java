package engine;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import java.util.List;

//public interface DBRepository extends CrudRepository<DBQuiz, Long> {
public interface DBRepository extends PagingAndSortingRepository<DBQuiz, Long> {

    //List<DBQuiz> findByNameContaining(String value);
    @Query("SELECT q FROM DBQuiz q WHERE q.title LIKE %:value%")
    List<DBQuiz> findByNameLike(@Param("value") String value);
}

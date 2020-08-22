package engine;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SolRepository extends CrudRepository<Solutions, Long> {
//public interface SolRepository extends PagingAndSortingRepository<Solutions, Long> {

    //@Query("SELECT c FROM Solutions c where c.user.username = :username order by c.completedAt desc")

    @Query("SELECT s FROM Solutions s where s.user LIKE %:value% order by s.completedAt desc")
    Page<Solutions> findAllByUserOrderByCompletedAtDesc(@Param("value") String username, Pageable pageable);

    /*@Query("SELECT s FROM Solutions s where s.user LIKE %:value% order by s.completedAt desc")
    Page<String> findAllByUserOrderByCompletedAtDesc(@Param("value") String username, Pageable pageable);*/

    /*@Query("SELECT q FROM DBQuiz q WHERE q.title LIKE %:value%")
    List<DBQuiz> findByNameLike(@Param("value") String value);*/
}

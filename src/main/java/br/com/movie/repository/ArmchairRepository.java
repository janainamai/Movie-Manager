package br.com.movie.repository;

import br.com.movie.model.Armchair;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArmchairRepository extends JpaRepository<Armchair, Integer> {

    @Query(value = "select * from armchair " +
                        "where session_id = :sessionId " +
                        "order by letter_number", nativeQuery = true)
    List<Armchair> findBySessionIdGroupByLetterNumber(@Param("sessionId") Integer sessionId);

}

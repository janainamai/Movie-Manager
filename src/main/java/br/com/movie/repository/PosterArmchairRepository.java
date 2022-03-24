package br.com.movie.repository;

import br.com.movie.model.PosterArmchair;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PosterArmchairRepository extends JpaRepository<PosterArmchair, Integer> {

    @Query(value = "select * from poster_armchair " +
                        "where poster_id = :posterId " +
                        "order by letter_number", nativeQuery = true)
    List<PosterArmchair> findByPosterIdGroupByLetterNumber(@Param("posterId") Integer posterId);

}

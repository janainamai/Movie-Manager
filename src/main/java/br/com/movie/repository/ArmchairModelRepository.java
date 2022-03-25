package br.com.movie.repository;

import br.com.movie.model.ArmchairModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArmchairModelRepository extends JpaRepository<ArmchairModel, Integer> {

    List<ArmchairModel> findByRoomId(Integer roomId);

    void deleteByRoomIdAndLetter(Integer roomId, String letter);

    @Query(value = "select * from armchair_model " +
            "where room_id = :roomId " +
            "order by letter, number", nativeQuery = true)
    List<ArmchairModel> findByRoomIdGroupByLetterNumber(@Param("roomId") Integer roomId);
}

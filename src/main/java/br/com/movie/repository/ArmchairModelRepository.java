package br.com.movie.repository;

import br.com.movie.model.ArmchairModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArmchairModelRepository extends JpaRepository<ArmchairModel, Integer> {

    List<ArmchairModel> findByRoomId(Integer roomId);

    void deleteByRoomIdAndLetter(Integer roomId, String letter);
}

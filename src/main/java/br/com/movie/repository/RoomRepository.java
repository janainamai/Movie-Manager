package br.com.movie.repository;

import br.com.movie.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoomRepository extends JpaRepository<Room, Integer> {

    Optional<List<Room>> findByNameContainingIgnoreCase(String name);

    Optional<Room> findByName(String nome);

}
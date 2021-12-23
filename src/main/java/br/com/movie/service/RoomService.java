package br.com.movie.service;

import br.com.movie.model.Room;
import br.com.movie.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService {

    @Autowired
    private RoomRepository roomRepository;

    public List<Room> list() {
        return roomRepository.findAll();
    }
}

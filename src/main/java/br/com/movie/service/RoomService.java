package br.com.movie.service;

import br.com.movie.exception.BadRequestException;
import br.com.movie.model.Room;
import br.com.movie.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoomService {

    private static final String ROOM_NOT_FOUND = "Room not found";

    @Autowired
    private RoomRepository roomRepository;

    public List<Room> list() {
        return roomRepository.findAll();
    }

    public Room findByName(String name) {
        return roomRepository.findByName(name)
                .orElseThrow(() -> new BadRequestException(ROOM_NOT_FOUND));
    }

    public Room save(Room room) {
        Optional<Room> optional = roomRepository.findByName(room.getName());
        if (optional.isPresent()) {
            throw new BadRequestException("There is already a room with this name");
        }

        return roomRepository.save(room);
    }

    public Room replace(Room room) {
        Optional<Room> optional = roomRepository.findById(room.getId());

        if (optional.isPresent()) {
            return roomRepository.save(room);
        } else {
            throw new BadRequestException(ROOM_NOT_FOUND);
        }
    }

}
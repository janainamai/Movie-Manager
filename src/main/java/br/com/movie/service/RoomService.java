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

    public Room findById(Integer id) {
        return roomRepository.findById(id)
                .orElseThrow(() -> new BadRequestException(ROOM_NOT_FOUND));
    }

    public Room findByName(String name) {
        return roomRepository.findByNameIgnoreCase(name)
                .orElseThrow(() -> new BadRequestException(ROOM_NOT_FOUND));
    }

    public Room save(Room room) {
        Optional<Room> optional = roomRepository.findByNameIgnoreCase(room.getName());
        if (optional.isPresent()) {
            throw new BadRequestException("There is already a room with this name");
        }

        room.setName(room.getName());
        return roomRepository.save(room);
    }

    public Room replace(Room room) {
        if (roomRepository.existsById(room.getId())) {
            return this.save(room);
        } else {
            throw new BadRequestException(ROOM_NOT_FOUND);
        }
    }

    public void delete(Integer id) {
        if (roomRepository.existsById(id)) {
            roomRepository.deleteById(id);
        } else {
            throw new BadRequestException(ROOM_NOT_FOUND);
        }
    }

}
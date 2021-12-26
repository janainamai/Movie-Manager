package br.com.movie.service;

import br.com.movie.model.Room;
import br.com.movie.repository.RoomRepository;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static org.apache.logging.log4j.util.Strings.isBlank;

@Service
public class RoomService {

    private static final String ROOM_NOT_FOUND = "Room not found";

    @Autowired
    private RoomRepository roomRepository;

    public List<Room> list() {
        return roomRepository.findAll();
    }

    public Room findById(Integer id) {
        Optional<Room> optional = roomRepository.findById(id);

        if(optional.isPresent()) {
            return optional.get();
        } else {
            throw new ServiceException(ROOM_NOT_FOUND);
        }
    }

    public Room save(Room room) {
        validate(room);

        Optional<Room> optional = roomRepository.findByName(room.getName());
        if(optional.isPresent()) {
            throw new ServiceException("There is already a room with this name");
        }

        return roomRepository.save(room);
    }

    public Room replace(Room room) {
        validate(room);

        Optional<Room> optional = roomRepository.findById(room.getId());

        if(optional.isPresent()) {
            return roomRepository.save(room);
        } else {
            throw new ServiceException(ROOM_NOT_FOUND);
        }
    }

    private void validate(Room room) {
        if(isBlank(room.getName())) {
            throw new ServiceException("The name cannot be null");
        }
    }

}

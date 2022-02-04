package br.com.movie.service;

import br.com.movie.exception.BadRequestException;
import br.com.movie.model.Armchair;
import br.com.movie.model.Room;
import br.com.movie.model.Row;
import br.com.movie.model.dto.BuildRoom;
import br.com.movie.repository.ArmchairRepository;
import br.com.movie.repository.RoomRepository;
import br.com.movie.repository.RowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static br.com.movie.common.Constants.*;

@Service
public class RoomService {

    private static final String ROOM_NOT_FOUND = "Room not found";

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private RowRepository rowRepository;

    @Autowired
    private ArmchairRepository armchairRepository;

    public List<Room> list() {
        return roomRepository.findAll();
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

        return roomRepository.save(room);
    }

    public Room replace(Room room) {
        if (roomRepository.existsById(room.getId())) {
            return this.save(room);
        } else {
            throw new BadRequestException(ROOM_NOT_FOUND);
        }
    }

    public void deleteById(Integer id) {
        if (roomRepository.existsById(id)) {
            roomRepository.deleteById(id);
        } else {
            throw new BadRequestException(ROOM_NOT_FOUND);
        }
    }

    @Transactional
    public Room buildRoom(BuildRoom input) {
        Room room = input.getRoom();
        List<Row> rows = generateRows(input.getNumberOfRows());

        for (Row row: rows) {
            List<Armchair> chairs = generateChairs(input.getArmchairPerRow(), row.getLetter());
            row.setArmchairs(chairs);
        }

        room.setRows(rows);
        return room;
    }

    private List<Row> generateRows(Integer numberOfRows) {
        List<Row> rows = new ArrayList<>();

        for (int r = 0; r < numberOfRows; r++) {
            Row row = new Row();
            row.setLetter(ALPHABET.get(r));

            rows.add(row);
        }

        rowRepository.saveAll(rows);
        return rows;
    }

    private List<Armchair> generateChairs(Integer numberOfArmchairs, String letter) {
        List<Armchair> chairs = new ArrayList<>();

        for (int a = 1; a <= numberOfArmchairs; a++) {
            Armchair chair = new Armchair();
            chair.setLetter(letter);
            chair.setNumber(a);

            chairs.add(chair);
        }

        armchairRepository.saveAll(chairs);
        return chairs;
    }
}
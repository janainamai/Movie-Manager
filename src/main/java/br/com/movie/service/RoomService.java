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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public Room buildRoom(BuildRoom input) {
        List<Row> rows = generateRowsAndArmchair(input.getNumberOfRows(), input.getArmchairPerRow());

        Room room = input.getRoom();
        room.setRows(rows);

        roomRepository.save(room);
        return room;
    }

    private List<Row> generateRowsAndArmchair(Integer numberOfRows, Integer armchairsPerRow) {
        List<String> alphabet = List.of("A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K",
                "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z");

        ArrayList<Row> rows = new ArrayList<>();

        for (int r = 0; r < numberOfRows; r++) {
            Row row = new Row();
            row.setLetter(alphabet.get(r));
            row.setArmchairs(new ArrayList<>());

            for (int a = 1; a <= armchairsPerRow; a++) {
                Armchair armchair = new Armchair();
                armchair.setCode(alphabet.get(r) + a);
                armchairRepository.save(armchair);

                row.getArmchairs().add(armchair);
            }
            rowRepository.save(row);
            rows.add(row);

        }

        return rows;
    }
}
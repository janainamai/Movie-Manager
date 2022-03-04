package br.com.movie.service;

import br.com.movie.exception.BadRequestException;
import br.com.movie.model.Armchair;
import br.com.movie.model.Room;
import br.com.movie.model.Row;
import br.com.movie.model.dto.ChangeNumberArmchairsInRowInput;
import br.com.movie.model.dto.RoomSaveInput;
import br.com.movie.repository.ArmchairRepository;
import br.com.movie.repository.RoomRepository;
import br.com.movie.repository.RowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static br.com.movie.common.Constants.ALPHABET;

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

    @Transactional
    public Room buildRoom(RoomSaveInput input) {
        Room roomToSave = new Room();
        roomToSave.setName(input.getName());
        Room room = save(roomToSave);

        List<Row> rows = generateRows(input.getNumberOfRows());

        for (Row row: rows) {
            List<Armchair> chairs = generateChairs(input.getArmchairPerRow(), row.getLetter());
            row.setArmchairs(chairs);
        }

        room.setRows(rows);
        roomRepository.save(room);
        return room;
    }

    @Transactional
    public List<Armchair> getArmchairsByRoomId(Integer roomId) {
        Room room = findById(roomId);

        List<Armchair> chairs = new ArrayList<>();

        for (Row row: room.getRows()) {
            chairs.addAll(row.getArmchairs());
        }

        return chairs;
    }

    @Transactional
    public Room changeNumberArmchairsInRow(ChangeNumberArmchairsInRowInput input) {
        Room room = findById(input.getRoomId());
        room.getRows().removeIf(r -> input.getLetterRow().equals(r.getLetter()));

        Row row = Row.builder()
                .letter(input.getLetterRow())
                .armchairs(generateChairs(input.getNumberOfArmchairs(), input.getLetterRow()))
                .build();

        rowRepository.save(row);
        room.getRows().add(row);
        return roomRepository.save(room);
    }

    @Transactional(readOnly = true)
    public List<Room> findByNameContainingIgnoreCase(String name) {
        return roomRepository.findByNameContainingIgnoreCase(name)
                .orElseThrow(() -> new BadRequestException(ROOM_NOT_FOUND));
    }

    @Transactional
    private Room findById(Integer id) {
        return roomRepository.findById(id)
                .orElseThrow(() -> new BadRequestException(ROOM_NOT_FOUND));
    }

    @Transactional
    private Room save(Room room) {
        Optional<Room> optional = roomRepository.findByName(room.getName());
        if (optional.isPresent()) {
            throw new BadRequestException("There is already a room with this name");
        }

        return roomRepository.save(room);
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
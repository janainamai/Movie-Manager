package br.com.movie.service;

import br.com.movie.exception.BadRequestException;
import br.com.movie.model.ArmchairModel;
import br.com.movie.model.Room;
import br.com.movie.model.dto.ChangeNumberArmchairsInRowInput;
import br.com.movie.model.dto.RoomSaveInput;
import br.com.movie.repository.ArmchairModelRepository;
import br.com.movie.repository.RoomRepository;
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
    private ArmchairModelRepository armchairModelRepository;

    @Transactional(readOnly = true)
    public List<Room> list() {
        return roomRepository.findAll();
    }

    @Transactional
    public Room buildRoom(RoomSaveInput input) {
        Room room = save(new Room(input.getName()));

        List<String> letters = generateRowLetters(input.getNumberOfRows());

        for (String letter : letters) {
            generateChairs(input.getArmchairPerRow(), letter, room.getId());
        }

        return room;
        // poderia retornar um RoomSaveOutput com lista de cadeiras junto
    }

    @Transactional(readOnly = true)
    public List<ArmchairModel> getArmchairsByRoomId(Integer roomId) {
        return armchairModelRepository.findByRoomId(roomId);
    }

    @Transactional
    public List<ArmchairModel> changeNumberArmchairsInRow(ChangeNumberArmchairsInRowInput input) {
        armchairModelRepository.deleteByRoomIdAndLetter(input.getRoomId(), input.getLetterRow());

        return generateChairs(input.getNumberOfArmchairs(), input.getLetterRow(), input.getRoomId());
    }

    @Transactional(readOnly = true)
    public List<Room> findByNameContainingIgnoreCase(String name) {
        return roomRepository.findByNameContainingIgnoreCase(name)
                .orElseThrow(() -> new BadRequestException(ROOM_NOT_FOUND));
    }

    @Transactional(readOnly = true)
    public Room findById(Integer id) {
        return roomRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("No room was found with id " + id));
    }

    private Room save(Room room) {
        Optional<Room> optional = roomRepository.findByNameIgnoreCase(room.getName());
        if (optional.isPresent()) {
            throw new BadRequestException("There is already a room with this name");
        }

        return roomRepository.save(room);
    }

    private List<String> generateRowLetters(Integer numberOfRows) {
        List<String> rows = new ArrayList<>();

        for (int r = 0; r < numberOfRows; r++) {
            rows.add(ALPHABET.get(r));
        }

        return rows;
    }

    private List<ArmchairModel> generateChairs(Integer numberOfArmchairsPerRow, String letter, Integer roomId) {
        List<ArmchairModel> chairs = new ArrayList<>();

        for (int a = 1; a <= numberOfArmchairsPerRow; a++) {
            ArmchairModel chair = new ArmchairModel();
            chair.setRoomId(roomId);
            chair.setLetter(letter);
            chair.setNumber(a);

            chairs.add(chair);
        }

        armchairModelRepository.saveAll(chairs);
        return chairs;
    }

}
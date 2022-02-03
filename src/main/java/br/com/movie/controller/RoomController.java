package br.com.movie.controller;

import br.com.movie.model.Room;
import br.com.movie.model.dto.BuildRoom;
import br.com.movie.model.dto.RoomPost;
import br.com.movie.model.dto.RoomPut;
import br.com.movie.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/room")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @GetMapping
    public ResponseEntity<List<Room>> list() {
        return ResponseEntity.ok(roomService.list());
    }

    @GetMapping(path = "/find")
    public ResponseEntity<Room> findByName(@RequestParam String name) {
        return ResponseEntity.ok(roomService.findByName(name));
    }

    @PostMapping
    public ResponseEntity<Room> save(@RequestBody @Valid RoomPost room) {
        return new ResponseEntity<>(roomService.save(room.toEntity()), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Room> replace(@RequestBody @Valid RoomPut room) {
        return ResponseEntity.ok(roomService.replace(room.toEntity()));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
        roomService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/build")
    public ResponseEntity<Room> buildRoom(@RequestBody @Valid BuildRoom input) {
        return ResponseEntity.ok(roomService.buildRoom(input));
    }
}
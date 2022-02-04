package br.com.movie.controller;

import br.com.movie.model.Armchair;
import br.com.movie.model.Room;
import br.com.movie.model.dto.RoomPost;
import br.com.movie.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
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

    @PostMapping("/save")
    public ResponseEntity<Room> save(@RequestBody @Valid RoomPost input) {
        return ResponseEntity.ok(roomService.buildRoom(input));
    }

    @GetMapping(path = "/getArmchairsByRoomId/{roomId}")
    public ResponseEntity<List<Armchair>> getArmchairsByRoomId(@PathVariable @NotNull Integer roomId) {
        return ResponseEntity.ok(roomService.getArmchairsByRoomId(roomId));
    }
}
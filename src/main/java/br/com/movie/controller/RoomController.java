package br.com.movie.controller;

import br.com.movie.model.ArmchairModel;
import br.com.movie.model.Room;
import br.com.movie.model.dto.ChangeNumberArmchairsInRowInput;
import br.com.movie.model.dto.RoomSaveInput;
import br.com.movie.model.dto.RoomSaveOutput;
import br.com.movie.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

import static org.springframework.http.ResponseEntity.*;

@RestController
@RequestMapping("/room")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @GetMapping(path = "/list")
    public ResponseEntity<List<Room>> list() {
        return ok(roomService.list());
    }

    @GetMapping(path = "/findByNameContainingIgnoreCase/{name}")
    public ResponseEntity<List<Room>> findByNameContainingIgnoreCase(@PathVariable @NotEmpty String name) {
        return ok(roomService.findByNameContainingIgnoreCase(name));
    }

    @PostMapping("/save")
    public ResponseEntity<RoomSaveOutput> save(@RequestBody @Valid RoomSaveInput input) {
        return ok(roomService.buildRoom(input));
    }

    @GetMapping(path = "/getArmchairsByRoomId/{roomId}")
    public ResponseEntity<List<ArmchairModel>> getArmchairsByRoomId(@PathVariable @NotNull Integer roomId) {
        return ok(roomService.getArmchairsByRoomId(roomId));
    }

    @PostMapping("/changeNumberArmchairsInRow")
    public ResponseEntity<List<ArmchairModel>> changeNumberArmchairsInRow(@RequestBody @Valid ChangeNumberArmchairsInRowInput input) {
        return ok(roomService.changeNumberArmchairsInRow(input));
    }
}
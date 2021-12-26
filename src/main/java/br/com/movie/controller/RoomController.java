package br.com.movie.controller;

import br.com.movie.model.Room;
import br.com.movie.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/room")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @GetMapping
    public List<Room> list() {
        return roomService.list();
    }

    @GetMapping(path = "/{id}")
    public Room findById(@PathVariable Integer id) {
        return roomService.findById(id);
    }

    @PostMapping
    public Room save(@RequestBody Room room) {
        return roomService.save(room);
    }

    @PutMapping
    public Room replace(@RequestBody Room room) {
        return roomService.replace(room);
    }
}

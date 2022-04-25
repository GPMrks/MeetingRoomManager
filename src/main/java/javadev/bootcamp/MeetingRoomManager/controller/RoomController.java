package javadev.bootcamp.MeetingRoomManager.controller;

import javadev.bootcamp.MeetingRoomManager.exception.ResourceNotFoundException;
import javadev.bootcamp.MeetingRoomManager.model.Room;
import javadev.bootcamp.MeetingRoomManager.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @GetMapping("/rooms")
    public List<Room> getAllRooms() {
        return roomService.getAllRooms();
    }

    @GetMapping("/rooms/{id}")
    public ResponseEntity<Room> getRoomById(@PathVariable("id") Long id) throws ResourceNotFoundException {
        Room room = roomService.getRoomById(id);
        return ResponseEntity.ok().body(room);
    }

    @PostMapping("/rooms")
    public ResponseEntity<Room> createRoom(@Valid @RequestBody Room room) {
        Room createdRoom = roomService.createRoom(room);
        URI uri = URI.create(String.format("/rooms", room.getName()));
        return ResponseEntity.created(uri).body(createdRoom);
    }

    @PutMapping("/rooms/{id}")
    public ResponseEntity<Room> updateRoom(@PathVariable("id") Long id, @Valid @RequestBody Room roomDetails) throws ResourceNotFoundException {
        final Room updateRoom = roomService.updateRoom(id, roomDetails);
        return ResponseEntity.ok().body(updateRoom);

    }

    @DeleteMapping("/rooms/{id}")
    public ResponseEntity deleteRoom(@PathVariable("id") Long id) throws ResourceNotFoundException {
        Map<String, Boolean> roomDeleted = roomService.deleteRoom(id);
        return ResponseEntity.ok().body(roomDeleted);
    }

}




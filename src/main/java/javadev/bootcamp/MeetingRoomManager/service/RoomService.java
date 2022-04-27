package javadev.bootcamp.MeetingRoomManager.service;

import javadev.bootcamp.MeetingRoomManager.exception.ResourceNotFoundException;
import javadev.bootcamp.MeetingRoomManager.model.Room;
import javadev.bootcamp.MeetingRoomManager.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RoomService {

    @Autowired
    private RoomRepository roomRepository;


    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    public Room getRoomById(Long id) throws ResourceNotFoundException {
        Room room = roomRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Room not found with ID: " + id));
        return room;
    }

    public Room createRoom(Room room) {
        return roomRepository.save(room);
    }

    public Room updateRoom(Long id, Room roomDetails) throws ResourceNotFoundException {

        Room room = roomRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Room not found with ID: " + id));

        room.setName(roomDetails.getName());
        room.setDate(roomDetails.getDate());
        room.setStartingTime(roomDetails.getStartingTime());
        room.setEndingTime(roomDetails.getEndingTime());

        final Room updateRoom = roomRepository.save(room);

        return updateRoom;

    }

    public Map<String, Boolean> deleteRoom(Long id) throws ResourceNotFoundException {

        Room room = roomRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Room not found with ID: " + id));

        roomRepository.delete(room);

        Map<String, Boolean> response = new HashMap<>();
        response.put("Deleted", Boolean.TRUE);

        return response;
    }
}

package javadev.bootcamp.MeetingRoomManager.repository;

import javadev.bootcamp.MeetingRoomManager.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

}

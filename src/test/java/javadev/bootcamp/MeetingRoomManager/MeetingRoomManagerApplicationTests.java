package javadev.bootcamp.MeetingRoomManager;

import javadev.bootcamp.MeetingRoomManager.exception.ResourceNotFoundException;
import javadev.bootcamp.MeetingRoomManager.model.Room;
import javadev.bootcamp.MeetingRoomManager.service.RoomService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
class MeetingRoomManagerApplicationTests {

	@Autowired
	private RoomService roomService;

	@Test
	public void getRoomTest() {

		// given - setup or precondition
		Room room = Room.builder().id(1L).name("Java 8").date("06/26/2022").startingTime("15h00").endingTime("18h00").build();

		// when - action or the testing
		List<Room> allRooms = roomService.getAllRooms();

		// then - very output
		assertNotNull(allRooms);
	}

	@Test
	public void getRoomByIdTest() throws ResourceNotFoundException {

		// given - setup or precondition
		Room room = Room.builder().id(1L).name("Java 8").date("06/26/2022").startingTime("15h00").endingTime("18h00").build();
		Room savedRoom = roomService.createRoom(room);

		// when - action or the testing
		Room roomById = roomService.getRoomById(savedRoom.getId());

		// then - very output
		assertEquals(room.toString(), roomById.toString());
	}

	@Test
	public void exceptionTest() throws ResourceNotFoundException {

		// given - setup or precondition
		Room room = Room.builder().id(1L).name("Java 8").date("06/26/2022").startingTime("15h00").endingTime("18h00").build();
		Room savedRoom = roomService.createRoom(room);

		Exception exception = assertThrows(ResourceNotFoundException.class, () -> {
			Room roomById = roomService.getRoomById(2L);
		});

		// when - action or the testing
		String expectedMessage = "Room not found with ID: " + 2L;
		String actualMessage = exception.getMessage();

		// then - very output
		assertTrue(actualMessage.contains(expectedMessage));
	}

	@Test
	public void createRoomTest() {

		// given - setup or precondition
		Room room = Room.builder().id(1L).name("Java 8").date("06/26/2022").startingTime("15h00").endingTime("18h00").build();

		// when - action or the testing
		Room savedRoom = roomService.createRoom(room);

		// then - very output
		assertEquals(room.toString(), savedRoom.toString());
	}

	@Test
	public void updateRoomTest() throws ResourceNotFoundException {

		// given - setup or precondition
		Room room = Room.builder().id(1L).name("Java 8").date("06/26/2022").startingTime("15h00").endingTime("18h00").build();
		Room savedRoom = roomService.createRoom(room);
		Room roomToUpdate = Room.builder().id(1L).name("Java 9").date("06/26/2022").startingTime("15h00").endingTime("20h00").build();

		// when - action or the testing
		Room updatedRoom = roomService.updateRoom(room.getId(), roomToUpdate);

		// then - very output
		assertEquals(updatedRoom.toString(), roomToUpdate.toString());
	}

	@Test
	public void deleteRoomTest() throws ResourceNotFoundException {

		// given - setup or precondition
		Room room = Room.builder().id(1L).name("Java 8").date("06/26/2022").startingTime("15h00").endingTime("18h00").build();

		// when - action or the testing
		var testResult = roomService.deleteRoom(room.getId());

		Map<String, Boolean> response = new HashMap<>();
		response.put("Deleted", Boolean.TRUE);

		// then - very output
		assertEquals(response, testResult);
	}

}

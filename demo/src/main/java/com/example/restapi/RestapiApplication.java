package com.example.restapi;

import com.example.restapi.room.Room;
import com.example.restapi.room.RoomService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class RestapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestapiApplication.class, args);
		RoomService.makeRoom("room1", "Rom 1", 10);
		RoomService.makeRoom("room2", "Rom 2", 10);
		RoomService.makeRoom("room3", "Rom 3", 10);
	}
}

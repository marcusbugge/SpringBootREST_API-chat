package com.example.restapi;

import com.example.restapi.room.Room;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class RestapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestapiApplication.class, args);
		Room.makeRoom("1", "Rom 1", 10);
		Room.makeRoom("2", "Rom 2", 10);
		Room.makeRoom("3", "Rom 3", 10);
	}
}

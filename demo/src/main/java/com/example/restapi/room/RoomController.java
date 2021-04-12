package com.example.restapi.room;


import com.example.restapi.Data;
import com.example.restapi.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class RoomController {

    RoomService roomService;


    @Autowired
    RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping("/api/room/{roomID}/users")
    public ArrayList<User> room(@PathVariable String roomID) {
        return roomService.findConnectedUsers(roomID);
    }

    @GetMapping("/api/rooms")
    public ArrayList<Room> showAllRooms() {
        return Data.availableRooms;
    }

    @PostMapping("/api/rooms")
    public Room addRoom(@RequestBody Room newRoom) {
        roomService.addRoom(newRoom);
        return newRoom;
    }
}

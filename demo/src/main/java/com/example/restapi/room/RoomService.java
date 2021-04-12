package com.example.restapi.room;

import com.example.restapi.Data;
import com.example.restapi.user.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class RoomService {

    Data data = new Data();

    public RoomService() {
        addUserToRoom(new User(1, "Feppe"));
        addUserToRoom(new User(1, "Henke"));
        addUserToRoom(new User(1, "Brede"));
    }

    public void addUserToRoom(User user) {
        Room.connectedUsers.add(user);
    }

    public void addRoom(Room newRoom) {
        data.getAvailableRooms().add(newRoom);
    }

    public ArrayList<User> findConnectedUsers(String roomID) {
        for (Room room : data.getAvailableRooms()) {
            if (room.getRoomID().equals(roomID)) {
                return room.getConnectedUsers();
            }
        }
        return new ArrayList<>();
    }

    public static Room findRoomByID(String roomID) {
        ArrayList<Room> roomList = Data.availableRooms;
        for (Room room : roomList) {
            if (room.getRoomID().equals(roomID)) {
                return room;
            }
        }
        return null;
    }
}

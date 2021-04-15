package com.example.restapi.room;

import com.example.restapi.Data;
import com.example.restapi.message.Message;
import com.example.restapi.user.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class RoomService {

    Data data = new Data();

    public RoomService() {

    }

    public void addUserToRoom(User user, String roomID) {
        Room room = findRoomByID(roomID);
        System.out.println(room);
        if (room != null) {
            room.getConnectedUsers().add(user);
        }
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
        for (Room room : Data.availableRooms) {
            if (room.getRoomID().equals(roomID)) {
                return room;
            }
        }
        return null;
    }

    public static void makeRoom(String roomID, String roomName, Integer roomSpace) {
        Data.availableRooms.add(new Room(roomID, roomName, roomSpace));
    }

    public void addMessage(Room room, Message message) {
        room.messages.add(message);
    }

    public ArrayList<Message> getMessagesFromRoomID(String roomID) {
        for (Room room : Data.availableRooms) {
            if (room.getRoomID().equals(roomID)) {
                return room.getMessages();
            }
        }
        return new ArrayList<>();
    }
}

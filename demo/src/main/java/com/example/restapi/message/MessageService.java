package com.example.restapi.message;

import com.example.restapi.Data;
import com.example.restapi.room.Room;
import com.example.restapi.room.RoomService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;

@Service
public class MessageService {

    Data data = new Data();

    public ArrayList<Message> showMessagesByID(String roomID) {
        for (Room room : data.getAvailableRooms()) {
            if (room.getRoomID().equals(roomID)) {
                return room.getMessages();
            }
        }
        return new ArrayList<>();
    }

    public void addMessageToRoom(Message newMessage, String roomID) {
        Room room = RoomService.findRoomByID(roomID);
        room.addMessage(newMessage, roomID);

        System.out.println(room.getMessagesFromRoomID(roomID));
    }
}
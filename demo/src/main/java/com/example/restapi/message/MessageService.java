package com.example.restapi.message;

import com.example.restapi.Data;
import com.example.restapi.room.Room;
import com.example.restapi.room.RoomService;
import com.example.restapi.user.User;
import com.example.restapi.user.UserService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;

@Service
public class MessageService {

    Data data = new Data();
    RoomService roomService = new RoomService();
    UserService userService = new UserService();

    public ArrayList<Message> showMessagesByID(String roomID) {
        for (Room room : data.getAvailableRooms()) {
            if (room.getRoomID().equals(roomID)) {
                return room.getMessages();
            }
        }
        return new ArrayList<>();
    }

    public String addMessageToRoom(String newMessage, String roomID) {
        String inMessage = userService.removeFuckers(newMessage);
        Message message = splitMessage(inMessage);

        if (roomService.checkifUserIsInRoom(message.senderID, roomID)) {
            Room room = RoomService.findRoomByID(roomID);

            roomService.addMessage(room, message);
            return "Message: " + message.content + " is added to room: " + roomID + "!";

        }
        return "You are not connected to room: " + roomID + "!";
    }

    private Message splitMessage(String newMessage) {
        String[] array = newMessage.split(";");
        return new Message(Integer.parseInt(array[0]), findUserByID(Integer.parseInt(array[0])), array[1]);
    }

    public String findUserByID(Integer userID) {
        User user = userService.findUserByID(userID);
        return user.getName();
    }
}

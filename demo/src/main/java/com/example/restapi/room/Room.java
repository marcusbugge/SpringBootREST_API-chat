package com.example.restapi.room;

import com.example.restapi.Data;
import com.example.restapi.message.Message;
import com.example.restapi.user.User;

import java.util.ArrayList;

public class Room {

    String roomID;
    String roomName;
    Integer roomSpace;

    public ArrayList<User> connectedUsers = new ArrayList<>();
    public ArrayList<Message> messages = new ArrayList<>();

    public Room(String roomID, String roomName, Integer roomSpace) {
        this.roomID = roomID;
        this.roomName = roomName;
        this.roomSpace = roomSpace;

    }

    public ArrayList<Message> getMessages() {
        return messages;
    }


    public ArrayList<User> getConnectedUsers() {
        return connectedUsers;
    }

    public  void setConnectedUsers(ArrayList<User> connectedUsers) {
        this.connectedUsers = connectedUsers;
    }

    public String getRoomID() {
        return roomID;
    }

    public void setRoomID(String roomID) {
        this.roomID = roomID;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public Integer getRoomSpace() {
        return roomSpace;
    }

    public void setRoomSpace(Integer roomSpace) {
        this.roomSpace = roomSpace;
    }

    @Override
    public String toString() {
        return "Room{" +
                "roomID='" + roomID + '\'' +
                ", roomName='" + roomName + '\'' +
                ", roomSpace=" + roomSpace +
                '}';
    }
}

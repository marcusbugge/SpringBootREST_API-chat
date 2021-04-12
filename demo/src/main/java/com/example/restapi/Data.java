package com.example.restapi;

import com.example.restapi.message.Message;
import com.example.restapi.room.Room;
import com.example.restapi.user.User;

import java.util.ArrayList;

public class Data {

    public static final String api_userPath = "http://localhost:8080/api/users";
    public static final String api_roomPath = "http://localhost:8080/api/rooms";

    public static ArrayList<User> connectedUsers = new ArrayList<>();
    public static ArrayList<Room> availableRooms = new ArrayList<>();

    public static ArrayList<Message> messages = new ArrayList<>();

    public ArrayList<User> getConnectedUsers() {
        return connectedUsers;
    }

    public void setConnectedUsers(ArrayList<User> connectedUsers) {
        this.connectedUsers = connectedUsers;
    }

    public ArrayList<Room> getAvailableRooms() {
        return availableRooms;
    }

    public void setAvailableRooms(ArrayList<Room> availableRooms) {
        this.availableRooms = availableRooms;
    }
}

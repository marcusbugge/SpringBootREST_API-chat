package com.example.restapi.user;

import com.example.restapi.room.Room;

import java.util.ArrayList;

public class User {
    Integer id;
    String name;
    ArrayList<String> joinedRooms;

    public User(Integer id, String name, ArrayList<String> joinedRooms) {
        this.id = id;
        this.name = name;
        this.joinedRooms = joinedRooms;

        this.joinedRooms = new ArrayList<>();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getJoinedRooms() {
        return joinedRooms;
    }

    public void setJoinedRooms(ArrayList<String> joinedRooms) {
        this.joinedRooms = joinedRooms;
    }

}

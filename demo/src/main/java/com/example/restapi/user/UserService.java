package com.example.restapi.user;

import com.example.restapi.Data;
import com.example.restapi.room.Room;
import com.example.restapi.user.User;
import org.apache.logging.log4j.message.Message;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@org.springframework.stereotype.Service
public class UserService {

    AtomicLong nextId = new AtomicLong();

    public void RegistrerUser(User newUser) {
        newUser.setId((int) nextId.incrementAndGet());
        Data.connectedUsers.add(newUser);
        System.out.println(Data.connectedUsers);
    }


    public String findUsers(String roomID) {
        return null;
    }

    public User findUserByID(String userID) {
        /*for (User user : Data.connectedUsers) {
            if (user.getId() = userID)
        }*/
        return null;
    }
}

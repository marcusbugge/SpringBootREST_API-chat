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
public class UserService <T> {

    AtomicLong nextId = new AtomicLong();

    public void RegistrerUser(String newUser) {
        String username = removeFuckers(newUser);
        System.out.println(username);
        if (!userFound(newUser)) {
            User user = new User((int) nextId.incrementAndGet(), username, null);
            Data.connectedUsers.add(user);

        }
    }

    public String removeFuckers(String newUser) {
        return newUser.replace("\"", "");
    }

    private boolean userFound(String newUser) {
        User user = findUserByName(newUser);
        if (user != null) {
            return true;
        }
        return false;
    }


    public User findUserByID(Integer userID) {
        for (User user : Data.connectedUsers) {
            if (user.getId().equals(userID)) {
                return user;
            }
        }
        return null;
    }

    public User findUserByName(String userName) {
        System.out.println(Data.connectedUsers);
        for (User user : Data.connectedUsers) {
            if (user.getName().equals(userName)) {
                return user;
            }
        }
        return null;
    }

    public Integer findUserID(String name) {
        User user = findUserByName(name);
        System.out.println(user);
        return user.getId();
    }
}

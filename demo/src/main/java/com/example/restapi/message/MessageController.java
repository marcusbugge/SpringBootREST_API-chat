package com.example.restapi.message;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class MessageController {

    MessageService messageService = new MessageService();

    @GetMapping("/api/room/room1/messages")
    public ArrayList<Message> showMessages1() {
        return messageService.showMessagesByID("room1");
    }

    @GetMapping("/api/room/room2/messages")
    public ArrayList<Message> showMessages2() {
        return messageService.showMessagesByID("room2");
    }

    @GetMapping("/api/room/room3/messages")
    public ArrayList<Message> showMessages3() {
        return messageService.showMessagesByID("room3");
    }

    @PostMapping("/api/room/{room-id}/messages")
    public String newMessage(@RequestBody String newMessage, @PathVariable("room-id") String roomID) {
        System.out.println(newMessage);
        return messageService.addMessageToRoom(newMessage, roomID);

    }
}

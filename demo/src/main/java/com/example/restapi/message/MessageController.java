package com.example.restapi.message;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class MessageController {

    MessageService messageService = new MessageService();

    @GetMapping("/api/room/1/messages")
    public ArrayList<Message> showMessages1() {
        return messageService.showMessagesByID("1");
    }

    @GetMapping("/api/room/2/messages")
    public ArrayList<Message> showMessages2() {
        return messageService.showMessagesByID("2");
    }

    @GetMapping("/api/room/3/messages")
    public ArrayList<Message> showMessages3() {
        return messageService.showMessagesByID("3");
    }

    @PostMapping("/api/room/{room-id}/messages")
    public Message newMessage(@RequestBody Message newMessage, @PathVariable("room-id") String roomID) {
        messageService.addMessageToRoom(newMessage, roomID);
        return newMessage;
    }
}

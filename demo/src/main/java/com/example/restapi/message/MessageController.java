package com.example.restapi.message;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class MessageController {

    MessageService messageService = new MessageService();

    @GetMapping("/api/room/{room-id}/messages")
    public ArrayList<Message> showMessages(@PathVariable String roomID) {
        return messageService.showMessagesByID(roomID);
    }

    @GetMapping("/api/room/messages")
    public ArrayList<Message> showMessagesTEst() {
        return messageService.showMessagesByID("10");
    }

    @PostMapping("/api/room/{room-id}/messages")
    public Message newMessage(@RequestBody Message newMessage, @PathVariable("room-id") String roomID) {
        messageService.addMessageToRoom(newMessage, roomID);
        return newMessage;
    }

    @PostMapping("/api/room/messages")
    public Message newMessageTest(@RequestBody Message newMessage) {
        return newMessage;
        // return messageService.addMessageToRoom(newMessage, roomID);
    }
}

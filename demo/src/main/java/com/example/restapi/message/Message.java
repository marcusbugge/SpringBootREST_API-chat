package com.example.restapi.message;

public class Message {
    Integer senderID;
    String sender;
    String content;

    public Message(Integer senderID, String sender, String content) {
        this.senderID = senderID;
        this.sender = sender;
        this.content = content;
    }

    public Integer getId() {
        return senderID;
    }

    public void setId(Integer id) {
        this.senderID = id;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return sender + ": " + content;
    }
}

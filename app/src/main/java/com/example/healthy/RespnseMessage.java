package com.example.healthy;

public class RespnseMessage {
    String textMessage;
    boolean isUser;
    String time;

    public RespnseMessage(String textMessage, boolean isUser, String time) {
        this.textMessage = textMessage;
        this.isUser = isUser;
        this.time = time;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTextMessage() {
        return textMessage;
    }

    public void setTextMessage(String textMessage) {
        this.textMessage = textMessage;
    }

    public boolean isUser() {
        return isUser;
    }

    public void setUser(boolean user) {
        isUser = user;
    }
}

package com.example.healthy;

public class RespnseMessage {
    String textMessage;
    boolean isUser;

    public RespnseMessage(String textMessage, boolean isUser) {
        this.textMessage = textMessage;
        this.isUser = isUser;
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

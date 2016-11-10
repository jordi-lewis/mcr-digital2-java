package com.manchesterdigital.chatroom;

import java.util.Date;

public class Message {

    private String text;
    private long date;

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }
}

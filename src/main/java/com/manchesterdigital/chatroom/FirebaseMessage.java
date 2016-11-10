package com.manchesterdigital.chatroom;

/**
 * Created by humaira.atcha on 10/11/2016.
 */
public class FirebaseMessage {
    private String message;
    private long date;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }
}

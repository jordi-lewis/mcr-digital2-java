package com.manchesterdigital.chatroom;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by humaira.atcha on 10/11/2016.
 */
public class FirebaseReader {
    public List<FirebaseMessage> readMessages(String jsonMessages) {
        List<FirebaseMessage> messages = new ArrayList<>();

        JSONObject jsonObject;
        jsonObject = new JSONObject(jsonMessages);

        Iterator<String> it = jsonObject.keys();
        while (it.hasNext()) {
            String key = it.next();
            String value = jsonObject.getJSONObject(key).getString("message");
            long date = jsonObject.getJSONObject(key).getLong("date");

            FirebaseMessage message = new FirebaseMessage();
            message.setDate(date);
            message.setMessage(value);
            messages.add(message);
        }

        return messages;
    }
}

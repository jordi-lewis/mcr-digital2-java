package com.manchesterdigital.chatroom;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

import javax.inject.Named;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


@Named
public class FirebaseService {

    // TEMP this needs to be the actual message service
    Map<String, String> messages = new HashMap<>();


    public void fireBase() throws FileNotFoundException{

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setServiceAccount(new FileInputStream("src/main/resources/manchesterdigitalchatroom-cfe7321d933a.json"))
                .setDatabaseUrl("https://manchesterdigitalchatroom.firebaseio.com/")
                .build();
        FirebaseApp fba = FirebaseApp.initializeApp(options);
        fba.getOptions();
    }

    public Map<String, String> getAllMessages() {
        return messages;
    }

    public String addMessage(String message) {
        String messageId = UUID.randomUUID().toString();

        messages.put(messageId, message);


        return messageId;
    }


}

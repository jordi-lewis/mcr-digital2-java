package com.manchesterdigital.chatroom;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import javax.inject.Named;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


@Named
public class FirebaseService {

    private static final String DB_URL = "https://error404teamnamenotfoundchat.firebaseio.com/user.json?auth=2Fwv6XMZ4wUchEp6iASb2LVOsGSKiDoqHHrbmqQy";

    // TEMP this needs to be the actual message service
    Map<String, String> messages = new HashMap<>();
    Map<String, String> users = new HashMap<>();


    public void fireBase() throws FileNotFoundException {

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setServiceAccount(new FileInputStream("src/main/resources/manchesterdigitalchatroom-cfe7321d933a.json"))
                .setDatabaseUrl("https://error404teamnamenotfoundchat.firebaseio.com/2Fwv6XMZ4wUchEp6iASb2LVOsGSKiDoqHHrbmqQy")
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

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.postForEntity(DB_URL, message, String.class);

        return messageId;
    }

    public Map<String, String> getAllUsers() {
        return users;
    }

    public String addUser(String user) {
        String userId = UUID.randomUUID().toString();

        users.put(userId, user);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.postForEntity(DB_URL, user, String.class);

        return userId;
    }

}

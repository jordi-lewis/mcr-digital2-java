package com.manchesterdigital.chatroom;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import javax.inject.Named;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;


@Named
public class FirebaseService {

    private static final String DB_USER_URL = "https://error404teamnamenotfoundchat.firebaseio.com/user.json?auth=2Fwv6XMZ4wUchEp6iASb2LVOsGSKiDoqHHrbmqQy";
    private static final String DB_MESSAGES_URL = "https://error404teamnamenotfoundchat.firebaseio.com/messages.json?auth=2Fwv6XMZ4wUchEp6iASb2LVOsGSKiDoqHHrbmqQy";

    // TEMP this needs to be the actual message service
    List<Message> messages = new ArrayList<>();
    Map<String, String> users = new HashMap<>();


    public void fireBase() throws FileNotFoundException {

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setServiceAccount(new FileInputStream("src/main/resources/manchesterdigitalchatroom-cfe7321d933a.json"))
                .setDatabaseUrl("https://error404teamnamenotfoundchat.firebaseio.com/2Fwv6XMZ4wUchEp6iASb2LVOsGSKiDoqHHrbmqQy")
                .build();
        FirebaseApp fba = FirebaseApp.initializeApp(options);
        fba.getOptions();
    }

    public List<Message> getAllMessages()
    {
        RestTemplate restTemplate = new RestTemplate();
//        ResponseEntity<FirebaseMessage[]> messages = restTemplate.getForEntity(DB_MESSAGES_URL, new ParameterizedTypeReference<Map<String, Message>>);
        ResponseEntity<String> jsonMessages = restTemplate.getForEntity(DB_MESSAGES_URL, String.class);

        FirebaseReader reader = new FirebaseReader();

        List<Message> response = new ArrayList<>();
        for (FirebaseMessage fbMessage :  reader.readMessages(jsonMessages.getBody())) {
            Message message = new Message();
            message.setText(fbMessage.getMessage());
            message.setDate(fbMessage.getDate());
            response.add(message);
        }

        return response;
    }

    public String addMessage(String messageStr) {
        String messageId = UUID.randomUUID().toString();

        Message message = new Message();
        message.setText("Hello");
        messages.add(message);

        FirebaseMessage fbMessage = new FirebaseMessage();
        fbMessage.setMessage(messageStr);
        fbMessage.setDate(new Date().getTime());

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<FirebaseMessage> response = restTemplate.postForEntity(DB_MESSAGES_URL, fbMessage, FirebaseMessage.class);

        return messageId;
    }

    public Map<String, String> getAllUsers() {
        return users;
    }

    public String addUser(String user) {
        String userId = UUID.randomUUID().toString();

        users.put(userId, user);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.postForEntity(DB_USER_URL, user, String.class);

        return userId;
    }

}

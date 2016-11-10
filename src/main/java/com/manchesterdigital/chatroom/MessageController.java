package com.manchesterdigital.chatroom;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.inject.Inject;
import java.io.FileNotFoundException;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class MessageController {


    @Inject
    FirebaseService firebaseService;

    private static final Logger logger = LoggerFactory
            .getLogger(MessageController.class);

    @RequestMapping("/")
    public String index() throws FileNotFoundException {

        firebaseService.fireBase();

        return "Greetings from Spring Boot!";
    }

    @RequestMapping(method=RequestMethod.GET, produces = "Application/json", value="message")
    public @ResponseBody
    List<Message> getAllMessages() {

        List<Message> messages = firebaseService.getAllMessages();

//        Message message = new Message();
//        message.setText("Hello");
//        messages.add(message);
//        Message message1 = new Message();
//        message1.setText("space 'n' shit");
//        messages.add(message1);
//        Message message2 = new Message();
//        message2.setText("rdxtcyvubino");
//        messages.add(message2);

//        Map<String, String> allMessages = new HashMap<>();
//        allMessages.putAll(firebaseService.getAllMessages());

        logger.debug("Returning {} message", messages.size());

        return messages;
    }

    @RequestMapping(method= RequestMethod.POST, produces = "Application/json", value="message")
    public ResponseEntity<?> addMessage(@RequestBody String message) {

        String messageId = firebaseService.addMessage(message);

        // Response
        Map<String,Object> model = new HashMap<>();

        model.put("id", messageId);
        model.put("content", message);

        logger.debug("created user ID {}", messageId);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(messageId).toUri();
        return ResponseEntity.created(location).build();
    }

}
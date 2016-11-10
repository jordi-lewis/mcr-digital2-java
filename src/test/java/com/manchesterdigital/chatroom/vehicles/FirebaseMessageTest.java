package com.manchesterdigital.chatroom.vehicles;

import com.manchesterdigital.chatroom.FirebaseMessage;
import com.manchesterdigital.chatroom.FirebaseReader;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by humaira.atcha on 10/11/2016.
 */
public class FirebaseMessageTest {

    String threeMessages = "{\"-KW7lURtBpeTGtXEm_qS\": {\"message\": \"Hello\"},\"-KW7lgxQr6OVgz9KiPGi\": {\"message\": \"Hello Edd\"},\"-KW7ozLfIv4Lus9ldXoh\": {\"message\": \"Hello Again\"}}";
    String oneMessage = "{\"-KW7lURtBpeTGtXEm_qS\": {\"message\": \"Hello\"}}";

    @Test
    public void FirebaseParserTest(){
        FirebaseReader reader = new FirebaseReader();
        List<FirebaseMessage> messages = reader.readMessages(oneMessage);

        assertThat(messages.get(0).getMessage(), is("Hello"));
    }

    @Test
    public void testThreeMessages() {

        FirebaseReader reader = new FirebaseReader();
        List<FirebaseMessage> messages = reader.readMessages(threeMessages);

        assertThat(messages.get(0).getMessage(), is("Hello"));
        assertThat(messages.get(2).getMessage(), is("Hello Edd"));
        assertThat(messages.get(1).getMessage(), is("Hello Again"));


    }


}

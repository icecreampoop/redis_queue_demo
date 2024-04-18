package tfip.pck.day25.day25_redisqueuedemo.services;

import java.io.StringReader;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

@Service
public class MessageSubscriber implements MessageListener{

    String id;
    String returnMessage;

    @Override
    public void onMessage(Message message, @Nullable byte[] pattern) {
        
        String data = new String(message.getBody());
        JsonReader jReader = Json.createReader(new StringReader(data));
        JsonObject jObject = jReader.readObject();
        id = jObject.getString("id");
        returnMessage = jObject.getString("message");
        System.out.printf("%s: \n%s <<<\n", id, returnMessage);
    }
    
}

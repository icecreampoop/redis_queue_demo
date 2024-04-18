package tfip.pck.day25.day25_redisqueuedemo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import jakarta.json.Json;
import jakarta.json.JsonObjectBuilder;

@Service
public class MessagePublish {

    @Autowired
    @Qualifier("myredis")
    private RedisTemplate<String, String> template;


    public void queue(String userID, String message) {
        ListOperations<String, String> listOps = template.opsForList();
        JsonObjectBuilder jBuilder = Json.createObjectBuilder()
                .add("id", userID)
                .add("message", message);

        String jString = jBuilder.build().toString();
        listOps.leftPush("myqueue", jString);
    }
}


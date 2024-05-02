package org.example.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import static java.lang.System.out;

@Service
public class KafkaConsumerExample {
    @KafkaListener(topicPattern = "oneTopic", groupId = "my_oneTopic")
    public void sendMessage(String text) {
        out.println( "Consumer " + text);
    }
}

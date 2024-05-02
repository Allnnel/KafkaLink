package org.example.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import static java.lang.System.out;
@Service
public class KafkaProducerExample {
    private final KafkaTemplate<String, String> kafkaTemplate;

    public KafkaProducerExample(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(String message) {
        out.println("Producer " + message); kafkaTemplate.send( "oneTopic", message);
    }
}

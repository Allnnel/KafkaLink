package org.example.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.model.Cat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerExample {

    private KafkaTemplate<String, String> kafkaStringTemplate;
    private final KafkaTemplate<String, Cat> kafkaTemplateClass;

    @Autowired
    public KafkaProducerExample(KafkaTemplate<String, String> kafkaStringTemplate, KafkaTemplate<String, Cat> kafkaTemplateClass) {
        this.kafkaStringTemplate = kafkaStringTemplate;
        this.kafkaTemplateClass = kafkaTemplateClass;
    }

    public void sendMessage(String message) {
        System.out.println("Producer " + message);
        kafkaStringTemplate.send("oneTopic", message);
    }


    public void sendClass(Cat cat) {
        System.out.println("Producer " + cat.getName());
        kafkaTemplateClass.send("catTopic", cat);
    }
}

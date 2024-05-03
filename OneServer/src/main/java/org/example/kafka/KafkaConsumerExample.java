package org.example.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.example.model.Cat;

@Service
public class KafkaConsumerExample {

    @KafkaListener(topics = "oneTopic", groupId = "my_oneTopic", containerFactory = "stringKafkaListenerContainerFactory")
    public void receiveString(String text) {
        System.out.println("Получено сообщение из Kafka: " + text);
    }


    @KafkaListener(topicPattern = "catTopic", groupId = "my_oneTopic_cat", containerFactory = "kafkaListenerContainerFactory")
    public void sendClass(Cat cat) {
        System.out.println("Получен объект Cat из Kafka: " + cat.getName());
    }
}

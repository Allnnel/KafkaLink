package org.example.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.example.model.Cat;

/**
 * Сервисный класс для обработки сообщений, полученных из Apache Kafka.
 */
@Service
public class KafkaConsumerExample {

    /**
     * Метод для обработки строковых сообщений, полученных из Kafka.
     * @param text Поступившее строковое сообщение.
     */
    @KafkaListener(topics = "oneTopic", groupId = "my_oneTopic", containerFactory = "stringKafkaListenerContainerFactory")
    public void receiveString(String text) {
        System.out.println("Получено сообщение из Kafka: " + text);
    }

    /**
     * Метод для обработки объектов класса Cat, полученных из Kafka.
     * @param cat Объект класса Cat.
     */
    @KafkaListener(topicPattern = "catTopic", groupId = "my_oneTopic_cat", containerFactory = "kafkaListenerContainerFactory")
    public void sendClass(Cat cat) {
        System.out.println("Получен объект Cat из Kafka: " + cat.getName());
    }
}


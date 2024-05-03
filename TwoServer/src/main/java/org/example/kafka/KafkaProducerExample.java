package org.example.kafka;

import org.example.model.Cat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

/**
 * Сервисный класс для отправки сообщений в Apache Kafka.
 */
@Service
public class KafkaProducerExample {

    private final KafkaTemplate<String, String> kafkaTemplateString;
    private final KafkaTemplate<String, Cat> kafkaTemplateClass;

    /**
     * Конструктор класса KafkaProducerExample.
     * @param kafkaTemplateString KafkaTemplate для отправки строковых сообщений.
     * @param kafkaTemplateClass KafkaTemplate для отправки сообщений, содержащих объекты класса Cat.
     */
    @Autowired
    public KafkaProducerExample(KafkaTemplate<String, String> kafkaTemplateString, KafkaTemplate<String, Cat> kafkaTemplateClass) {
        this.kafkaTemplateString = kafkaTemplateString;
        this.kafkaTemplateClass = kafkaTemplateClass;
    }

    /**
     * Метод для отправки строки в указанную тему Kafka.
     * @param message Сообщение для отправки.
     */
    public void sendMessage(String message) {
        System.out.println("Producer " + message);
        kafkaTemplateString.send("oneTopic", message);
    }

    /**
     * Метод для отправки объекта класса Cat в указанную тему Kafka.
     * @param cat Объект класса Cat для отправки.
     */
    public void sendClass(Cat cat) {
        System.out.println("Producer " + cat.getName());
        kafkaTemplateClass.send("catTopic", cat);
    }
}

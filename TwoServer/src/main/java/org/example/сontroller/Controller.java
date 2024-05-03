package org.example.сontroller;

import org.example.kafka.KafkaProducerExample;
import org.example.model.Cat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Контроллер для обработки HTTP-запросов связанных с Apache Kafka.
 */
@RestController
@RequestMapping("kafka")
public class Controller {

    private final KafkaProducerExample producer;
    @Autowired
    public Controller(KafkaProducerExample producer) {
        this.producer = producer;
    }

    /**
     * Метод обработки POST-запроса для отправки текстового сообщения в Apache Kafka.
     * @param text Текстовое сообщение для отправки.
     * @return Ответ об успешном выполнении операции.
     */
    @PostMapping("post/text")
    public String postText(@RequestParam String text) {
        producer.sendMessage(text);
        return "Success";
    }

    /**
     * Метод обработки POST-запроса для отправки объекта класса Cat в Apache Kafka.
     * @param cat Объект класса Cat для отправки.
     * @return Ответ об успешном выполнении операции.
     */
    @PostMapping("post/class")
    public String postClass(@RequestBody Cat cat) {
        producer.sendClass(cat);
        return "Success";
    }
}

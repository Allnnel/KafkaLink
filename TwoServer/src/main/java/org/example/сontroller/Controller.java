package org.example.controller;

import org.example.kafka.KafkaProducerExample;
import org.example.model.Cat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("kafka")
public class Controller {

    @Autowired
    private KafkaProducerExample consumer;

    @PostMapping("post/text")
    public String postText(@RequestParam String text) {
        consumer.sendMessage(text);
        return "Success";
    }
    @PostMapping("post/class")
    public String postClass(@RequestBody Cat cat) {
        consumer.sendClass(cat);
        return "Success";
    }
}

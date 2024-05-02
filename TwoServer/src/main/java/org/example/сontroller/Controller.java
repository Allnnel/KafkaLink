package org.example.controller;

import org.example.kafka.KafkaProducerExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("kafka")
public class Controller {

    @Autowired
    private KafkaProducerExample consumer;

    @PostMapping("post")
    public String post(@RequestParam String text) {
        consumer.sendMessage(text);
        return "Success";
    }
}

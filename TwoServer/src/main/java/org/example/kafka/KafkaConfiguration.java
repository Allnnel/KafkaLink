package org.example.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaConfiguration {

    public NewTopic oneTopic() {
        return new NewTopic("oneTopic", 1, (short) 1);
    }
}

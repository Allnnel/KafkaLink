package org.example.conf;

import org.apache.kafka.common.serialization.StringSerializer;
import org.example.model.Cat;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;
import java.util.HashMap;
import java.util.Map;

/**
 * Конфигурационный класс для настройки компонентов Apache Kafka.
 */
@EnableKafka
@Configuration
public class KafkaConfig {

    /**
     * Метод для создания и настройки KafkaTemplate для отправки и получения сообщений, где ключ и значение являются строками.
     * @return KafkaTemplate для работы с строковыми сообщениями.
     */
    @Bean
    public KafkaTemplate<String, String> kafkaStringTemplate() {
        return new KafkaTemplate<>(producerStringFactory());
    }

    /**
     * Фабричный метод для создания ProducerFactory с настройками для отправки сообщений, где ключ и значение являются строками.
     * @return ProducerFactory для работы с строковыми сообщениями.
     */
    @Bean
    public ProducerFactory<String, String> producerStringFactory() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(org.apache.kafka.clients.producer.ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        configProps.put(org.apache.kafka.clients.producer.ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(org.apache.kafka.clients.producer.ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        return new DefaultKafkaProducerFactory<>(configProps);
    }

    /**
     * Метод для создания и настройки KafkaTemplate для отправки и получения сообщений, где ключ - строка, а значение - объект класса Cat.
     * @return KafkaTemplate для работы с сообщениями, содержащими объекты класса Cat.
     */
    @Bean
    public KafkaTemplate<String, Cat> kafkaTemplate() {
        return new KafkaTemplate<>(producerCatFactory());
    }

    /**
     * Фабричный метод для создания ProducerFactory с настройками для отправки сообщений, где ключ - строка, а значение - объект класса Cat.
     * @return ProducerFactory для работы с сообщениями, содержащими объекты класса Cat.
     */
    @Bean
    public ProducerFactory<String, Cat> producerCatFactory() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(org.apache.kafka.clients.producer.ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        configProps.put(org.apache.kafka.clients.producer.ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(org.apache.kafka.clients.producer.ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return new DefaultKafkaProducerFactory<>(configProps);
    }
}


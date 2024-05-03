package org.example.conf;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.example.model.Cat;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.listener.SeekToCurrentErrorHandler;
import org.springframework.kafka.support.converter.StringJsonMessageConverter;
import org.springframework.util.backoff.FixedBackOff;
import org.springframework.kafka.listener.ErrorHandler;
/**
 * Конфигурационный класс для настройки компонентов Apache Kafka.
 */
@Configuration
public class KafkaConfig {

    /**
     * Метод для создания фабрики контейнеров Kafka для обработки строковых сообщений.
     * @param consumerFactory Фабрика консюмеров для строковых сообщений.
     * @return Фабрика контейнеров Kafka для строковых сообщений.
     */
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> stringKafkaListenerContainerFactory(
            ConsumerFactory<String, String> consumerFactory) {
        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory);
        factory.setErrorHandler(new CustomErrorHandler());

        factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.MANUAL);
        FixedBackOff backOff = new FixedBackOff();
        backOff.setInterval(1000L);
        backOff.setMaxAttempts(3);
        factory.setErrorHandler(new SeekToCurrentErrorHandler(backOff));
        return factory;
    }

    /**
     * Метод для создания фабрики контейнеров Kafka для обработки сообщений, содержащих объекты класса Cat.
     * @param consumerFactory Фабрика консюмеров для сообщений, содержащих объекты класса Cat.
     * @return Фабрика контейнеров Kafka для сообщений, содержащих объекты класса Cat.
     */
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Cat> kafkaListenerContainerFactory(
            ConsumerFactory<String, Cat> consumerFactory) {
        ConcurrentKafkaListenerContainerFactory<String, Cat> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory);
        factory.setMessageConverter(new StringJsonMessageConverter());
        factory.setErrorHandler(new CustomErrorHandler());

        factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.MANUAL);
        FixedBackOff backOff = new FixedBackOff();
        backOff.setInterval(1000L);
        backOff.setMaxAttempts(3);
        factory.setErrorHandler(new SeekToCurrentErrorHandler(backOff));
        return factory;
    }
}

/**
 * Обработчик ошибок для Kafka.
 */
class CustomErrorHandler implements ErrorHandler {

    /**
     * Метод обработки ошибки при получении сообщения из Kafka.
     * @param thrownException Возникшее исключение.
     * @param record Запись, вызвавшая исключение.
     */
    @Override
    public void handle(Exception thrownException, ConsumerRecord<?, ?> record) {
        System.err.println("Ошибка обработки сообщения из Kafka: " + thrownException.getMessage());
    }

    /**
     * Метод обработки ошибки при получении сообщения из Kafka.
     * @param thrownException Возникшее исключение.
     * @param record Запись, вызвавшая исключение.
     * @param consumer Консьюмер, связанный с записью.
     */
    @Override
    public void handle(Exception thrownException, ConsumerRecord<?, ?> record, Consumer<?, ?> consumer) {
        System.err.println("Ошибка обработки сообщения из Kafka: " + thrownException.getMessage());
    }
}

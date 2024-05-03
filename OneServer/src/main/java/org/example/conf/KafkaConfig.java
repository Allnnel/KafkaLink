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

@Configuration
public class KafkaConfig {

    @Bean
    public StringJsonMessageConverter jsonConverter() {
        return new StringJsonMessageConverter();
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> stringKafkaListenerContainerFactory(
            ConsumerFactory<String, String> consumerFactory) {
        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();

        factory.setConsumerFactory(consumerFactory);
//        factory.setMessageConverter(jsonConverter());
        factory.setErrorHandler(new CustomErrorHandler());

        // Устанавливаем ручное подтверждение
        factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.MANUAL);

        // Настройка повторной попытки при ошибках
        FixedBackOff backOff = new FixedBackOff();
        backOff.setInterval(1000L); // Интервал между повторными попытками (1 секунда)
        backOff.setMaxAttempts(3); // Максимальное количество повторных попыток (3 попытки)
        factory.setErrorHandler(new SeekToCurrentErrorHandler(backOff)); // Устанавливаем обработчик ошибок с повторной попыткой

        return factory;
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Cat> kafkaListenerContainerFactory(
            ConsumerFactory<String, Cat> consumerFactory) {
        ConcurrentKafkaListenerContainerFactory<String, Cat> factory = new ConcurrentKafkaListenerContainerFactory<>();

        factory.setConsumerFactory(consumerFactory);
        factory.setMessageConverter(new StringJsonMessageConverter());
        factory.setErrorHandler(new CustomErrorHandler());

        // Устанавливаем ручное подтверждение
        factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.MANUAL);

        // Настройка повторной попытки при ошибках
        FixedBackOff backOff = new FixedBackOff();
        backOff.setInterval(1000L); // Интервал между повторными попытками (1 секунда)
        backOff.setMaxAttempts(3); // Максимальное количество повторных попыток (3 попытки)
        factory.setErrorHandler(new SeekToCurrentErrorHandler(backOff)); // Устанавливаем обработчик ошибок с повторной попыткой

        return factory;
    }


}



// Обработчик ошибок
class CustomErrorHandler implements ErrorHandler {

    @Override
    public void handle(Exception thrownException, ConsumerRecord<?, ?> record) {
        System.err.println("Ошибка обработки сообщения из Kafka: " + thrownException.getMessage());
    }

    @Override
    public void handle(Exception thrownException, ConsumerRecord<?, ?> record, Consumer<?, ?> consumer) {
        System.err.println("Ошибка обработки сообщения из Kafka: " + thrownException.getMessage());
    }
}

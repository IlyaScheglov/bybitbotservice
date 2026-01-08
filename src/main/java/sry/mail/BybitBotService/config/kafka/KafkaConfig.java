package sry.mail.BybitBotService.config.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.ProducerFactory;

@Configuration
public class KafkaConfig {

    @Bean
    public KafkaTemplateAdapter kafkaTemplateAdapter(ProducerFactory<String, String> producerFactory,
                                                     ObjectMapper objectMapper) {
        return new KafkaTemplateAdapter(producerFactory, objectMapper);
    }
}

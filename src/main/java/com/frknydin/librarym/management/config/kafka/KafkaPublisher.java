package com.frknydin.librarym.management.config.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class KafkaPublisher {
    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaPublisher.class);

    private final KafkaTemplate<String,Object> kafkaTemplate;

    public KafkaPublisher(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publish(String topicName, Object message){
        try{
            kafkaTemplate.send(topicName,message);
        }catch (Exception e){
            String key = UUID.randomUUID().toString();
            LOGGER.info(String.format("Topic name: %s, Object: %s, Key: %s not to push kafka",
                    topicName,
                    message.toString(),
                    key));
        }
    }
}

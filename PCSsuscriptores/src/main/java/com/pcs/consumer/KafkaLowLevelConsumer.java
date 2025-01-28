package com.pcs.consumer;

import com.suscribers.avro.*;
import com.pcs.producer.KafkaLowLevelProducer;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;

@Configuration
@Slf4j
public class KafkaLowLevelConsumer {

    @Autowired
    KafkaLowLevelProducer kafkaLowLevelProducer;


    @KafkaListener(topics = "suscribers")
    public void process(ConsumerRecord<SuscriberKey, SuscriberValue> suscriber) {
        log.info("Received message from topic {} in partition {} and offset {} with key: {}",
                suscriber.topic(), suscriber.partition(), suscriber.offset(), suscriber.key());

        SuscriberKey suscriberKey = suscriber.key();
        SuscriberValue suscriberValue = suscriber.value();

        log.info("SuscriberKey: {} | SuscriberValue: {}", suscriberKey, suscriberValue);

        kafkaLowLevelProducer.send(suscriberKey, suscriberValue);
    }

}

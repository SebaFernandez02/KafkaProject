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


    @KafkaListener(topics = "suscriptions")
    public void process(ConsumerRecord<SuscriptionKey, SuscriptionValue> suscription) {
        log.info("Received message from topic {} in partition {} and offset {} with key: {}",
                suscription.topic(), suscription.partition(), suscription.offset(), suscription.key());

        SuscriptionKey suscriptionKey = suscription.key();
        SuscriptionValue suscriptionValue = suscription.value();

        log.info("SuscriberKey: {} | SuscriberValue: {}", suscriptionKey, suscriptionValue);

        kafkaLowLevelProducer.send(suscriptionKey, suscriptionValue);
    }

}

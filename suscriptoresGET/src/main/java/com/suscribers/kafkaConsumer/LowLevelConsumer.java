package com.suscribers.kafkaConsumer;


import com.suscribers.avro.SuscriberFinalKey;
import com.suscribers.avro.SuscriberFinalValue;
import com.suscribers.kafkaConsumer.mapper.SuscriberMapper;
import com.suscribers.service.SuscriberService;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;


@Configuration
@Slf4j
public class LowLevelConsumer {
    @Autowired
    private SuscriberService suscriberService;



    @KafkaListener(topics = "suscribers-final")
    public void process(ConsumerRecord<SuscriberFinalKey, SuscriberFinalValue> suscriber) {
        log.info("Received message from topic {} in partition {} and offset {} with key: {}",
                suscriber.topic(), suscriber.partition(), suscriber.offset(), suscriber.key());

        suscriberService.saveSuscriber(new SuscriberMapper().toDto(suscriber.key(), suscriber.value()));

        log.info("Suscriber with id: {} saved in repository", suscriber.key().getId());
    }



}


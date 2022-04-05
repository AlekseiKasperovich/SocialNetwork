package com.senla.consumer;

import com.senla.api.dto.mailing.MailingDto;
import org.apache.kafka.clients.consumer.ConsumerRecord;

public interface KafkaConsumer {
    void receive(ConsumerRecord<String, MailingDto> record);
}

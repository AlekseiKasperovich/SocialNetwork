package com.senla.consumer;

import com.senla.dto.mailing.MailingDto;
import com.senla.dto.constants.Type;
import com.senla.service.EmailService;
import com.senla.service.MessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class Consumer implements KafkaConsumer {

    private final MessageService messageService;
    private final EmailService emailService;

    @KafkaListener(topics = "${topic.name.consumer}")
    public void receive(ConsumerRecord<String, MailingDto> record) {
        log.info("message received: {}", record.value().getMessageText());
        if (Type.PRIVATE_MESSAGE.equals(record.value().getType())) {
            messageService.send(record.value().getMessageText());
        } else {
            emailService.send(record.value().getMessageText());
        }
    }

}

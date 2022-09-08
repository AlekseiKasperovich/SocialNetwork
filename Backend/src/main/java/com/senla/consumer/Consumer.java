package com.senla.consumer;

import com.senla.dto.constants.Type;
import com.senla.dto.mailing.MailingDto;
import com.senla.service.EmailService;
import com.senla.service.MessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
@ConditionalOnProperty(value = "kafka.enable", havingValue = "false", matchIfMissing = false)
public class Consumer implements KafkaConsumer {

    private final MessageService messageService;
    private final EmailService emailService;

    @KafkaListener(topics = "${topic.name.consumer}")
    public void receive(ConsumerRecord<String, MailingDto> message) {
        log.info("message received: {}", message.value().getMessageText());
        if (Type.PRIVATE_MESSAGE.equals(message.value().getType())) {
            messageService.send(message.value().getMessageText());
        } else {
            emailService.send(message.value().getMessageText());
        }
    }
}

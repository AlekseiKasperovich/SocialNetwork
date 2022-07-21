package com.senla.producer;

import com.senla.api.dto.mailing.MailingDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class Producer implements KafkaProducer {

    @Value("${topic.name.producer}")
    private String topicName;

    private final KafkaTemplate<String, MailingDto> kafkaTemplate;

    public String send(MailingDto mailingDto) {
        log.info("message sent: {}", mailingDto.getMessageText());
        kafkaTemplate.send(topicName, mailingDto);
        return "Published Successfully";
    }
}

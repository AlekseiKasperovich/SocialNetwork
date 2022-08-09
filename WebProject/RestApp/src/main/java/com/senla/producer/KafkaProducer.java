package com.senla.producer;

import com.senla.dto.mailing.MailingDto;

public interface KafkaProducer {
    String send(MailingDto mailingDto);
}

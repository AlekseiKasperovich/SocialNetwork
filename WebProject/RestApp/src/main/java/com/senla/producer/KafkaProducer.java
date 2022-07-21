package com.senla.producer;

import com.senla.api.dto.mailing.MailingDto;

public interface KafkaProducer {
    String send(MailingDto mailingDto);
}

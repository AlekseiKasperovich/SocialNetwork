package com.senla.controller;

import com.senla.api.dto.mailing.MailingDto;
import com.senla.producer.KafkaProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author Aliaksei Kaspiarovich
 */
@RestController
@RequestMapping(value = "/api/admin/mailing",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class AdminMailingController {

    private final KafkaProducer producer;

    @PostMapping
    public String createMailing(
            @Valid @RequestBody MailingDto mailingDto) {
        return producer.send(mailingDto);
    }
}

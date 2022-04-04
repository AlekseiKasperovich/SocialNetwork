package com.senla.controller;

import com.senla.api.dto.community.CommunityDto;
import com.senla.api.dto.mailing.MailingDto;
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
public class AdminController {


    @PostMapping
    public CommunityDto createMailing(
            @Valid @RequestBody MailingDto mailingDto) {
        return null;
    }
}

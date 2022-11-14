package com.senla.web.service.impl;

import com.senla.web.dto.message.MessageDto;
import com.senla.web.feign.MessageClient;
import com.senla.web.service.MessageService;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final MessageClient messageClient;

    @Override
    public Page<MessageDto> getMessages(UUID userId) {
        Pageable page = PageRequest.of(0, 20);
        return messageClient.getMessages(userId, page).getBody();
    }
}

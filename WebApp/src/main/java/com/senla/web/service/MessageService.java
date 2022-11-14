package com.senla.web.service;

import com.senla.web.dto.message.MessageDto;
import java.util.UUID;
import org.springframework.data.domain.Page;

public interface MessageService {

    Page<MessageDto> getMessages(UUID userId);
}

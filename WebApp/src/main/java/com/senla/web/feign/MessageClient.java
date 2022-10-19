package com.senla.web.feign;

import com.senla.web.dto.message.MessageDto;
import java.util.UUID;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@FeignClient(name = "MessageClient", url = ("${request.host}" + "/messages"))
public interface MessageClient {

    @GetMapping
    ResponseEntity<Page<MessageDto>> getMessages(@RequestParam UUID receiverId, Pageable page);
}

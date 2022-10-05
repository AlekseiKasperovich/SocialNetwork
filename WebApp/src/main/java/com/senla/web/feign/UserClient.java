package com.senla.web.feign;

import com.senla.web.dto.user.DtoUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

@Component
@FeignClient(name = "UserClient", url = ("${request.host}" + "/users"))
public interface UserClient {

    @GetMapping
    ResponseEntity<Page<DtoUser>> getUsers(Pageable page);
}

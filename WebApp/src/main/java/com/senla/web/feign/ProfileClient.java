package com.senla.web.feign;

import com.senla.web.dto.user.DtoUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "ProfileClient", url = "${request.host}")
public interface ProfileClient {

    @GetMapping(value = "users/profile")
    ResponseEntity<DtoUser> getProfile();
}

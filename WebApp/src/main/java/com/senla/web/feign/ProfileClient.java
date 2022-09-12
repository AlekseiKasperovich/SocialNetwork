package com.senla.web.feign;

import com.senla.web.dto.user.DtoUser;
import feign.Headers;
import feign.Param;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "ProfileClient", url = "${request.host}")
@Headers({"Authorization: {token}"})
public interface ProfileClient {

    @GetMapping(value = "/users/profile")
    ResponseEntity<DtoUser> getProfile(@Param("token") String token);
}

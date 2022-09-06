package com.senla.web.feign;

import com.senla.web.dto.token.TokenDto;
import com.senla.web.dto.user.DtoCreateUser;
import com.senla.web.dto.user.DtoUser;
import com.senla.web.dto.user.LoginUserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "AuthClient", url = "${request.host}")
public interface AuthClient {

    @PostMapping(
            value = "auth/registration",
            consumes = "application/json",
            produces = "application/json")
    ResponseEntity<DtoUser> createUser(@RequestBody DtoCreateUser createUserDto);

    @PostMapping(value = "auth/login", consumes = "application/json", produces = "application/json")
    TokenDto login(@RequestBody LoginUserDto loginUserDto);
}

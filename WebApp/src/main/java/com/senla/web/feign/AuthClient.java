package com.senla.web.feign;

import com.senla.web.dto.profile.EmailDto;
import com.senla.web.dto.token.TokenDto;
import com.senla.web.dto.user.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "AuthClient", url = "${request.host}")
public interface AuthClient {

    @PostMapping(value = "auth/registration")
    ResponseEntity<DtoUser> createUser(@RequestBody DtoCreateUser createUserDto);

    @PostMapping(value = "auth/login")
    ResponseEntity<TokenDto> login(@RequestBody LoginUserDto loginUserDto);

    @PostMapping(value = "auth/password/generate")
    void generatePassword(@RequestBody ForgotPasswordDto forgotPasswordDto);

    @PostMapping(value = "auth/password/reset")
    void resetPassword(ForgotPasswordDto forgotPasswordDto);

    @PostMapping(value = "auth/password/reset/validate")
    ResponseEntity<EmailDto> validateToken(TokenDto token);

    @PatchMapping(value = "auth/password/reset/change")
    void changePassword(ResetPasswordDto resetPasswordDto);
}

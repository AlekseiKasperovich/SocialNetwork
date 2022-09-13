package com.senla.web.feign;

import com.senla.web.dto.profile.ChangePasswordDto;
import com.senla.web.dto.profile.UpdateUserDto;
import com.senla.web.dto.user.DtoUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@Component
@FeignClient(name = "ProfileClient", url = ("${request.host}" + "/users/profile"))
public interface ProfileClient {

    @GetMapping
    ResponseEntity<DtoUser> getProfile();

    @PutMapping
    ResponseEntity<DtoUser> updateProfile(UpdateUserDto updateUserDto);

    @PatchMapping
    ResponseEntity<DtoUser> changePassword(ChangePasswordDto changePasswordDto);

    @DeleteMapping
    void deleteProfile();
}

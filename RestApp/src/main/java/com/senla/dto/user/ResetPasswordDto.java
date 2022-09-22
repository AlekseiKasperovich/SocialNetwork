package com.senla.dto.user;

import com.senla.validation.ValidPassword;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResetPasswordDto {

    @Email @NotBlank private String email;

    @Size(min = 5, max = 16)
    @ValidPassword
    @NotBlank
    private String password;

    @NotBlank private String matchingPassword;
}

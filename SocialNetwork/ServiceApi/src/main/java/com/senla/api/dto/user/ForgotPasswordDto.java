package com.senla.api.dto.user;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import lombok.Data;

/**
 *
 * @author Aliaksei Kaspiarovich
 */
@Data
public class ForgotPasswordDto {

    @Email
    @NotBlank
    private String email;

}

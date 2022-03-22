package com.senla.api.dto.user;

import com.senla.api.validation.ValidPassword;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import lombok.Data;
import com.senla.api.validation.FieldsValueMatch;

/**
 *
 * @author Aliaksei Kaspiarovich
 */
@Data
@FieldsValueMatch(field = "password",
        fieldMatch = "matchingPassword",
        message = "Passwords do not match!")
public class DtoCreateUser {

    @Email
    @NotBlank
    private String email;

    @ValidPassword
    @NotBlank
    private String password;

    @NotBlank
    private String matchingPassword;

}

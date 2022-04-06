package com.senla.api.dto.user;

import com.senla.api.validation.FieldsValueMatch;
import com.senla.api.validation.ValidPassword;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * @author Aliaksei Kaspiarovich
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
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

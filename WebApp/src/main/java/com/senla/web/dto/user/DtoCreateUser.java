package com.senla.web.dto.user;

import com.senla.web.validation.FieldsValueMatch;
import com.senla.web.validation.ValidPassword;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Aliaksei Kaspiarovich
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldsValueMatch(
        field = "password",
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

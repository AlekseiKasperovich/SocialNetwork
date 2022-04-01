package com.senla.api.dto.profile;

import com.senla.api.validation.FieldsValueMatch;
import com.senla.api.validation.ValidPassword;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

/**
 *
 * @author Aliaksei Kaspiarovich
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldsValueMatch(field = "password",
        fieldMatch = "matchingPassword",
        message = "Passwords do not match!")
public class ChangePasswordDto {

    @ValidPassword
    @NotBlank
    private String password;

    @NotBlank
    private String matchingPassword;
}

package com.senla.api.dto.profile;

import com.senla.api.validation.FieldsValueMatch;
import com.senla.api.validation.ValidPassword;
import javax.validation.constraints.NotBlank;
import lombok.Data;

/**
 *
 * @author Aliaksei Kaspiarovich
 */
@Data
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

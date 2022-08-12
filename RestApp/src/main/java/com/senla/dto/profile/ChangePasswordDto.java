package com.senla.dto.profile;

import com.senla.validation.FieldsValueMatch;
import com.senla.validation.ValidPassword;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/** @author Aliaksei Kaspiarovich */
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldsValueMatch(
        field = "password",
        fieldMatch = "matchingPassword",
        message = "Passwords do not match!")
public class ChangePasswordDto {

    @ValidPassword @NotBlank private String password;

    @NotBlank private String matchingPassword;
}

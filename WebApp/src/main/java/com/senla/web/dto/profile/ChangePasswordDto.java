package com.senla.web.dto.profile;

import com.senla.web.validation.ValidPassword;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/** @author Aliaksei Kaspiarovich */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChangePasswordDto {

    @Size(min = 5, max = 16)
    @ValidPassword
    @NotBlank
    private String password;

    @NotBlank private String matchingPassword;
}

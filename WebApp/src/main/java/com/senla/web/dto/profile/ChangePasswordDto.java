package com.senla.web.dto.profile;

import com.senla.web.validation.ValidPassword;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/** @author Aliaksei Kaspiarovich */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChangePasswordDto {

    @ValidPassword @NotBlank private String password;

    @NotBlank private String matchingPassword;
}

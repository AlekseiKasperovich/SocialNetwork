package com.senla.api.dto.profile;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.senla.api.dto.сonstants.Constants;
import com.senla.api.dto.сonstants.Gender;
import java.time.LocalDate;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import lombok.Data;

/**
 *
 * @author Aliaksei Kaspiarovich
 */
@Data
public class UpdateUserDto {

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @Past
    @JsonFormat(pattern = Constants.DATE_PATTERN)
    @NotNull
    private LocalDate birthday;

    @NotNull
    private Gender sex;

    @NotBlank
    private String phone;

}

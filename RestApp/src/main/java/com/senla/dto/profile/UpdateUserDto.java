package com.senla.dto.profile;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.senla.dto.constants.Gender;
import java.time.LocalDate;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/** @author Aliaksei Kaspiarovich */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserDto {

    @NotBlank private String firstName;

    @NotBlank private String lastName;

    @Past
    @JsonFormat(pattern = "dd.MM.yyyy")
    @NotNull
    private LocalDate birthday;

    @NotNull private Gender sex;

    @NotBlank private String phone;
}

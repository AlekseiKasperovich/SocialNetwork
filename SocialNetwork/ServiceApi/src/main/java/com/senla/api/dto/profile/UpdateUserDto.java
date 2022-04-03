package com.senla.api.dto.profile;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.senla.api.dto.сonstants.Gender;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDate;

/**
 * @author Aliaksei Kaspiarovich
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserDto {

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @Past
    @JsonFormat(pattern="${date.pattern}")
    @NotNull
    private LocalDate birthday;

    @NotNull
    private Gender sex;

    @NotBlank
    private String phone;

}

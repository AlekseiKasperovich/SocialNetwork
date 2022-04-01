package com.senla.api.dto.profile;

import com.senla.api.dto.сonstants.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDate;

/**
 *
 * @author Aliaksei Kaspiarovich
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserDto {

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @Past
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @NotNull
    private LocalDate birthday;

    @NotNull
    private Gender sex;

    @NotBlank
    private String phone;

}

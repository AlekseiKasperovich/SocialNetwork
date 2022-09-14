package com.senla.web.dto.profile;

import com.senla.web.dto.constants.Gender;
import java.time.LocalDate;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

/** @author Aliaksei Kaspiarovich */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserDto {

    @NotBlank private String firstName;

    @NotBlank private String lastName;

    @Past
    //    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @NotNull
    private LocalDate birthday;

    @NotNull private Gender sex;

    @NotBlank private String phone;
}

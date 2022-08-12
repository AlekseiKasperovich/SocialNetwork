package com.senla.dto.profile;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.senla.dto.constants.Gender;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/** @author Aliaksei Kaspiarovich */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserDto {

    private String firstName;
    private String lastName;

    @JsonFormat(pattern = "dd.MM.yyyy")
    private LocalDate birthday;

    private Gender sex;
    private String phone;
}

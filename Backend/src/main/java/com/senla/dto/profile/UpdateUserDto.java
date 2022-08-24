package com.senla.dto.profile;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/** @author Aliaksei Kaspiarovich */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateUserDto {

    private String firstName;
    private String lastName;

    @JsonFormat(pattern = "dd.MM.yyyy")
    private LocalDate birthday;

    private String sex;
    private String phone;
}

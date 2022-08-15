package com.senla.dto.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/** @author Aliaksei Kaspiarovich */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DtoUser {

    private UUID id;
    private String email;
    private String firstName;
    private String lastName;

    @JsonFormat(pattern = "dd.MM.yyyy")
    private LocalDate birthday;

    private String sex;
    private String phone;
    private String status;
}

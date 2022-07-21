package com.senla.api.dto.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * @author Aliaksei Kaspiarovich
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DtoUser {

    private Long id;
    private String email;
    private String firstName;
    private String lastName;

    @JsonFormat(pattern="dd.MM.yyyy")
    private LocalDate birthday;

    private String sex;
    private String phone;
    private String status;

}

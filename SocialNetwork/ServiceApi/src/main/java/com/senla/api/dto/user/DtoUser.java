package com.senla.api.dto.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

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

    @JsonFormat(pattern="${date.pattern}")
    private LocalDate birthday;

    private String sex;
    private String phone;
    private String status;

}

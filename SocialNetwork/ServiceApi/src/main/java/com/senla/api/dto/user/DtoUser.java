package com.senla.api.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

/**
 * @author Aliaksei Kaspiarovich
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DtoUser {

    private Long id;
    private String email;
    private String firstName;
    private String lastName;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate birthday;

    private String sex;
    private String phone;
    private String status;

}

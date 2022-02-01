package com.senla.api.dto.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.senla.api.dto.сonstants.Constants;
import java.time.LocalDate;
import lombok.Data;

/**
 *
 * @author Aliaksei Kaspiarovich
 */
@Data
public class DtoUser {

    private Long id;
    private String email;
    private String firstName;
    private String lastName;

    @JsonFormat(pattern = Constants.DATE_PATTERN)
    private LocalDate birthday;

    private String sex;
    private String phone;
    private String status;

}

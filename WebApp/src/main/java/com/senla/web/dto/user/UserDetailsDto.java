package com.senla.web.dto.user;

import com.senla.web.dto.constants.Status;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/** @author Aliaksei Kaspiarovich */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDetailsDto {

    private UUID id;
    private String email;
    private String password;
    private RoleDto role;
    private Status status;
}

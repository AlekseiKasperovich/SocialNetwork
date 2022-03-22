package com.senla.api.dto.user;

import com.senla.api.dto.сonstants.Status;
import lombok.Data;

/**
 *
 * @author Aliaksei Kaspiarovich
 */
@Data
public class UserDetailsDto {

    private String email;
    private String password;
    private RoleDto role;
    private Status status;
}

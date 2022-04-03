package com.senla.api.dto.user;

import com.senla.api.dto.сonstants.Status;
import lombok.*;

/**
 * @author Aliaksei Kaspiarovich
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDetailsDto {

    private String email;
    private String password;
    private RoleDto role;
    private Status status;
}

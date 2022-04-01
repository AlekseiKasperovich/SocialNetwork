package com.senla.api.dto.user;

import com.senla.api.dto.сonstants.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Aliaksei Kaspiarovich
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDetailsDto {

    private String email;
    private String password;
    private RoleDto role;
    private Status status;
}

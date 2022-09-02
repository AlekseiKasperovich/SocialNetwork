package com.senla.web.dto.user;

import com.senla.web.dto.constants.Roles;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/** @author Aliaksei Kaspiarovich */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleDto {

    private Roles name;
}

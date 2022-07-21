package com.senla.api.dto.user;

import com.senla.api.dto.сonstants.Roles;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Aliaksei Kaspiarovich
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleDto {

    private Roles name;
}

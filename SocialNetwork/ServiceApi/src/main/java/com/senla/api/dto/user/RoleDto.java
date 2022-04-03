package com.senla.api.dto.user;

import com.senla.api.dto.сonstants.Roles;
import lombok.*;

/**
 * @author Aliaksei Kaspiarovich
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleDto {

    private Roles name;
}

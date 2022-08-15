/* (C)2022 */
package com.senla.dto.community;

import com.senla.dto.user.DtoUser;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/** @author Aliaksei Kaspiarovich */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommunityDto {

    private UUID id;
    private String name;
    private String description;
    private List<DtoUser> followers;
}

package com.senla.dto.community;

import com.senla.dto.user.DtoUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Aliaksei Kaspiarovich
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommunityDto {

    private Long id;
    private String name;
    private String description;
    private List<DtoUser> followers;
}

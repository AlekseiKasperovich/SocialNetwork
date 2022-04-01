package com.senla.api.dto.community;

import com.senla.api.dto.user.DtoUser;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * @author Aliaksei Kaspiarovich
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommunityDto {

    private Long id;
    private String name;
    private String description;
    private List<DtoUser> followers;
}

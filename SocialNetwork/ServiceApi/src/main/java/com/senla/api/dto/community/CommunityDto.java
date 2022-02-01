package com.senla.api.dto.community;

import com.senla.api.dto.user.DtoUser;
import java.util.List;
import lombok.Data;

/**
 *
 * @author Aliaksei Kaspiarovich
 */
@Data
public class CommunityDto {

    private Long id;
    private String name;
    private String description;
    private List<DtoUser> followers;
}

package com.senla.api.dto.friendship;

import com.senla.api.dto.user.DtoUser;
import lombok.Data;

/**
 *
 * @author Aliaksei Kaspiarovich
 */
@Data
public class FriendshipDto {

    private Long id;
    private DtoUser sender;
    private DtoUser receiver;
    private Boolean accepted;
}

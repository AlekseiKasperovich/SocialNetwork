package com.senla.api.dto.friendship;

import com.senla.api.dto.user.DtoUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Aliaksei Kaspiarovich
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FriendshipDto {

    private Long id;
    private DtoUser sender;
    private DtoUser receiver;
    private Boolean accepted;
}

package com.senla.api.dto.friendship;

import com.senla.api.dto.user.DtoUser;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author Aliaksei Kaspiarovich
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FriendshipDto {

    private Long id;
    private DtoUser sender;
    private DtoUser receiver;
    private Boolean accepted;
}

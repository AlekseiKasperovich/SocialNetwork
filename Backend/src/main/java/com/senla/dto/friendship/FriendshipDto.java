package com.senla.dto.friendship;

import com.senla.dto.user.DtoUser;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/** @author Aliaksei Kaspiarovich */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FriendshipDto {

    private UUID id;
    private DtoUser sender;
    private DtoUser receiver;
    private Boolean accepted;
}

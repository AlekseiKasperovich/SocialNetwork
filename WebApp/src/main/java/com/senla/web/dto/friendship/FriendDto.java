package com.senla.web.dto.friendship;

import com.senla.web.dto.user.DtoUser;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FriendDto {

    private UUID id;
    private DtoUser friend;
}

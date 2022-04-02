package com.senla.mapper;

import com.senla.api.dto.friendship.FriendshipDto;
import com.senla.model.Friendship;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

@Mapper
public interface MapStructMapper {

    FriendshipDto friendshipToDto(Friendship friendship);

}

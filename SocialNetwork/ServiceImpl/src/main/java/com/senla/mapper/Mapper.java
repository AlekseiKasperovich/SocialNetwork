package com.senla.mapper;

import com.senla.api.dto.community.CommunityDto;
import com.senla.api.dto.community.CommunityMessageDto;
import com.senla.api.dto.community.CreateCommunityDto;
import com.senla.api.dto.event.CreateEventDto;
import com.senla.api.dto.event.EventDto;
import com.senla.api.dto.event.EventMessageDto;
import com.senla.api.dto.friendship.FriendshipDto;
import com.senla.api.dto.message.CreateMessageDto;
import com.senla.api.dto.message.MessageDto;
import com.senla.api.dto.profile.UpdateUserDto;
import com.senla.api.dto.user.DtoCreateUser;
import com.senla.api.dto.user.DtoUser;
import com.senla.api.dto.user.RoleDto;
import com.senla.api.dto.user.UserDetailsDto;
import com.senla.model.*;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;
import org.springframework.stereotype.Component;

/**
 * @author Aliaksei Kaspiarovich
 */
@Component
public class Mapper extends ConfigurableMapper {

    @Override
    //можно почитать про мапстракт очень часто исользуется в проектах в последнее время https://www.baeldung.com/mapstruct
    protected void configure(MapperFactory factory) {
        factory.classMap(Role.class, RoleDto.class)
                .byDefault()
                .register();

        factory.classMap(User.class, DtoUser.class)
                .byDefault()
                .register();

        factory.classMap(User.class, UserDetailsDto.class)
                .byDefault()
                .register();

        factory.classMap(User.class, DtoCreateUser.class)
                .exclude("matchingPassword")
                .byDefault()
                .register();

        factory.classMap(User.class, UpdateUserDto.class)
                .byDefault()
                .register();

        factory.classMap(Community.class, CommunityDto.class)
                .byDefault()
                .register();

        factory.classMap(Community.class, CreateCommunityDto.class)
                .byDefault()
                .register();

        factory.classMap(CommunityMessage.class, CommunityMessageDto.class)
                .byDefault()
                .register();

        factory.classMap(CommunityMessage.class, CreateMessageDto.class)
                .byDefault()
                .register();

        factory.classMap(Event.class, EventDto.class)
                .byDefault()
                .register();

        factory.classMap(Event.class, CreateEventDto.class)
                .byDefault()
                .register();

        factory.classMap(EventMessage.class, EventMessageDto.class)
                .byDefault()
                .register();

        factory.classMap(EventMessage.class, CreateMessageDto.class)
                .byDefault()
                .register();

        factory.classMap(Friendship.class, FriendshipDto.class)
                .byDefault()
                .register();

        factory.classMap(Message.class, MessageDto.class)
                .byDefault()
                .register();

        factory.classMap(Message.class, CreateMessageDto.class)
                .byDefault()
                .register();

    }
}

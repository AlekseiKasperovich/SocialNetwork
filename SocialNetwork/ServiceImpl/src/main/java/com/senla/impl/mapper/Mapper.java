package com.senla.impl.mapper;

import com.senla.api.dto.community.CommunityDto;
import com.senla.api.dto.community.CommunityMessageDto;
import com.senla.api.dto.event.EventDto;
import com.senla.api.dto.event.EventMessageDto;
import com.senla.api.dto.friendship.FriendshipDto;
import com.senla.api.dto.message.MessageDto;
import com.senla.api.dto.community.CreateCommunityDto;
import com.senla.api.dto.event.CreateEventDto;
import com.senla.api.dto.message.CreateMessageDto;
import com.senla.api.dto.user.CreateUser;
import com.senla.api.dto.profile.UpdateUserDto;
import com.senla.api.dto.user.DtoUser;
import com.senla.impl.model.Community;
import com.senla.impl.model.CommunityMessage;
import com.senla.impl.model.Event;
import com.senla.impl.model.EventMessage;
import com.senla.impl.model.Friendship;
import com.senla.impl.model.Message;
import com.senla.impl.model.User;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;
import org.springframework.stereotype.Component;

/**
 *
 * @author Aliaksei Kaspiarovich
 */
@Component
public class Mapper extends ConfigurableMapper {

    @Override
    protected void configure(MapperFactory factory) {
        factory.classMap(User.class, DtoUser.class)
                .byDefault()
                .register();

        factory.classMap(User.class, CreateUser.class)
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

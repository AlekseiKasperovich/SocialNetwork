package com.senla.dto.event;

import com.senla.dto.user.DtoUser;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/** @author Aliaksei Kaspiarovich */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventDto {

    private UUID id;
    private String name;
    private String description;
    private DtoUser author;
    private List<DtoUser> participants;
}

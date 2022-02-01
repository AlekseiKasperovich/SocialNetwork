package com.senla.api.dto.event;

import com.senla.api.dto.user.DtoUser;
import java.util.List;
import lombok.Data;

/**
 *
 * @author Aliaksei Kaspiarovich
 */
@Data
public class EventDto {

    private Long id;
    private String name;
    private String description;
    private DtoUser author;
    private List<DtoUser> participants;
}

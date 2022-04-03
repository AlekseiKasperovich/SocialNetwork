package com.senla.api.dto.event;

import com.senla.api.dto.user.DtoUser;
import lombok.*;

import java.util.List;

/**
 *
 * @author Aliaksei Kaspiarovich
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventDto {

    private Long id;
    private String name;
    private String description;
    private DtoUser author;
    private List<DtoUser> participants;
}

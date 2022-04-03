package com.senla.api.dto.event;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.senla.api.dto.user.DtoUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

/**
 *
 * @author Aliaksei Kaspiarovich
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventMessageDto {

    private Long id;

    @JsonFormat(pattern="${date.time.pattern}")
    private LocalDateTime posted;

    private String body;
    private DtoUser sender;
    private EventDto event;

}

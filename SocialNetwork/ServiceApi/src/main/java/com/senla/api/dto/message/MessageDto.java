package com.senla.api.dto.message;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.senla.api.dto.user.DtoUser;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

/**
 *
 * @author Aliaksei Kaspiarovich
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageDto {

    private Long id;

    @JsonFormat(pattern="${date.time.pattern}")
    private LocalDateTime posted;

    private String body;
    private DtoUser sender;
    private DtoUser receiver;
    private Boolean isPrivate;

}

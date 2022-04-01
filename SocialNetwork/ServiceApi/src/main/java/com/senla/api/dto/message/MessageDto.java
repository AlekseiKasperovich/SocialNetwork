package com.senla.api.dto.message;

import com.senla.api.dto.user.DtoUser;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

/**
 *
 * @author Aliaksei Kaspiarovich
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MessageDto {

    private Long id;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime posted;

    private String body;
    private DtoUser sender;
    private DtoUser receiver;
    private Boolean isPrivate;

}

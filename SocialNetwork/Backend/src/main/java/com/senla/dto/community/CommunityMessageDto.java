package com.senla.dto.community;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.senla.dto.user.DtoUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author Aliaksei Kaspiarovich
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommunityMessageDto {

    private Long id;

    @JsonFormat(pattern="dd.MM.yyyy HH:mm:ss")
    private LocalDateTime posted;

    private String body;
    private DtoUser sender;
    private CommunityDto community;

}

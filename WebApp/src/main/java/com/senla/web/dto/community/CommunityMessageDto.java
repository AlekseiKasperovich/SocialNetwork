package com.senla.web.dto.community;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.senla.web.dto.user.DtoUser;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/** @author Aliaksei Kaspiarovich */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommunityMessageDto {

    private UUID id;

    @JsonFormat(pattern = "dd.MM.yyyy HH:mm:ss")
    private LocalDateTime posted;

    private String body;
    private DtoUser sender;
    private CommunityDto community;
}

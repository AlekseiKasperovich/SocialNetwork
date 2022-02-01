package com.senla.api.dto.message;

import javax.validation.constraints.NotBlank;
import lombok.Data;

/**
 *
 * @author Aliaksei Kaspiarovich
 */
@Data
public class CreateMessageDto {

    @NotBlank
    private String body;
}

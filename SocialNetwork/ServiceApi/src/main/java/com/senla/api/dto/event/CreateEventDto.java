package com.senla.api.dto.event;

import javax.validation.constraints.NotBlank;
import lombok.Data;

/**
 *
 * @author Aliaksei Kaspiarovich
 */
@Data
public class CreateEventDto {

    @NotBlank
    private String name;

    @NotBlank
    private String description;
}

package com.senla.api.dto.event;

import lombok.*;

import javax.validation.constraints.NotBlank;

/**
 *
 * @author Aliaksei Kaspiarovich
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateEventDto {

    @NotBlank
    private String name;

    @NotBlank
    private String description;
}

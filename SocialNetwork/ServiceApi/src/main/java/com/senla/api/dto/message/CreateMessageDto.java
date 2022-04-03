package com.senla.api.dto.message;

import lombok.*;

import javax.validation.constraints.NotBlank;

/**
 *
 * @author Aliaksei Kaspiarovich
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateMessageDto {

    @NotBlank
    private String body;
}

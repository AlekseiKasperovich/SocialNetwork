package com.senla.api.dto.community;

import javax.validation.constraints.NotBlank;
import lombok.Data;

/**
 *
 * @author Aliaksei Kaspiarovich
 */
@Data
public class CreateCommunityDto {

    @NotBlank
    private String name;

    @NotBlank
    private String description;
}

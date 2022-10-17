package com.senla.web.dto.community;

import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/** @author Aliaksei Kaspiarovich */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateCommunityDto {

    @NotBlank private String name;

    @NotBlank private String description;
}

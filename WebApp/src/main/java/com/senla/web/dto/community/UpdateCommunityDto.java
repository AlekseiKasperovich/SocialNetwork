package com.senla.web.dto.community;

import java.util.UUID;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCommunityDto {

    @NotNull private UUID id;

    @NotBlank private String name;

    @NotBlank private String description;
}

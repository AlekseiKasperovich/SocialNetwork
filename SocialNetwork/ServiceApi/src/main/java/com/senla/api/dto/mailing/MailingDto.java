package com.senla.api.dto.mailing;

import com.senla.api.dto.сonstants.Type;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * @author Aliaksei Kaspiarovich
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MailingDto {

    @NotBlank
    private String messageText;

    private Type type;

}

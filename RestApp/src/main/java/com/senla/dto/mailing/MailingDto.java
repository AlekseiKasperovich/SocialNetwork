package com.senla.dto.mailing;

import com.senla.dto.constants.Type;
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

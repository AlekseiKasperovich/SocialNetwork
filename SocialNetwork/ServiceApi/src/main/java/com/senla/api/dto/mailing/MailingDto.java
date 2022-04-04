package com.senla.api.dto.mailing;

import com.senla.api.dto.сonstants.Type;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Aliaksei Kaspiarovich
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MailingDto {

    private String topic;
    private String messageText;
    private Type type;

}

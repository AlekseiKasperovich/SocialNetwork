package com.senla.api.exception;

import lombok.Builder;
import lombok.Data;

/**
 *
 * @author Aliaksei Kaspiarovich
 */
@Data
@Builder
public class ExceptionDetails {

    private String title;
    private String detail;
    private int status;
    private String message;
}

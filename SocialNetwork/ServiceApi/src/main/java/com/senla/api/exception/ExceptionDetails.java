package com.senla.api.exception;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author Aliaksei Kaspiarovich
 */
@Data
@Builder
public class ExceptionDetails {

    private LocalDateTime time;
    private String title;
    private String detail;
    private int status;
    private String message;
}

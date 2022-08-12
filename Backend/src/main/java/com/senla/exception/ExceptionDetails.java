package com.senla.exception;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;

/** @author Aliaksei Kaspiarovich */
@Data
@Builder
public class ExceptionDetails {

    private LocalDateTime time;
    private String title;
    private String detail;
    private int status;
    private String message;
}

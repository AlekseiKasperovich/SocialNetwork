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

    //Обычное ещё таймкод добавляют для мэссэджа, но это чисто для удобства
    private String title;
    private String detail;
    private int status;
    private String message;
}

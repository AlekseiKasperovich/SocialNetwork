package com.senla.api.dto.сonstants;

/**
 *
 * @author Aliaksei Kaspiarovich
 */
//ToDo удалить класс, сделать красивый ямл пропертей и накатить туда, если необходимо стоздать классы
public class Constants {

    //ToDo попробовать через ${} сослаться на параметр из проепертей или @DateTimeFormat(iso = ISO.DATE_TIME)
    public static final String DATE_PATTERN = "dd.MM.yyyy";
    public static final String DATE_TIME_PATTERN = "dd.MM.yyyy HH:mm:ss";

    public static final String EMAIL_HEADER = "email";
    public static final String QUESTION = "?";
    
    public static final String BEARER = "Bearer ";
    
    public static final String HOST_PORT = "http://backend:8081";

//    public static final String HOST_PORT = "http://localhost:8081";
}

package com.senla.property;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "request")
public class RequestProperty {

    private String id;
    private String email;
    private String host;
    private String question;
}

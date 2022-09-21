package com.senla.property;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "jwt")
public class JwtProperty {

    private String secret;
    private long tokenExpiration;
    private String authorization;
    private String bearer;
    private long linkExpiration;
}

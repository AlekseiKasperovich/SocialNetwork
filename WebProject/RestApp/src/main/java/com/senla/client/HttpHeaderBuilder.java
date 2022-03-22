package com.senla.client;

import org.springframework.http.HttpHeaders;

/**
 *
 * @author Aliaksei Kaspiarovich
 */
public interface HttpHeaderBuilder {

    HttpHeaders build();

    HttpHeaders build(String email);
}

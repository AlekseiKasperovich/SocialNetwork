package com.senla.client.impl;

import com.senla.client.HttpHeaderBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

/**
 * @author Aliaksei Kaspiarovich
 */
@Service
@RequiredArgsConstructor
public class HttpHeaderBuilderImpl extends CurrentUserService
        implements HttpHeaderBuilder {

    private final HttpHeaders headers;

    @Override
    public HttpHeaders build() {
        headers.set("${request.email}", getCurrentUserEmail());
        return headers;
    }

    @Override
    public HttpHeaders build(String email) {
        headers.set("${request.email}", email);
        return headers;
    }
}

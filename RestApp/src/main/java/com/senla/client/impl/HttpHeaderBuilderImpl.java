package com.senla.client.impl;

import com.senla.client.HttpHeaderBuilder;
import com.senla.property.RequestProperty;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

/** @author Aliaksei Kaspiarovich */
@Service
@RequiredArgsConstructor
public class HttpHeaderBuilderImpl extends CurrentUserService implements HttpHeaderBuilder {

    private final HttpHeaders headers;
    private final RequestProperty requestProperty;

    @Override
    public HttpHeaders build() {
        headers.set(requestProperty.getEmail(), getCurrentUserEmail());
        headers.set(requestProperty.getId(), getCurrentUserId());
        return headers;
    }

    @Override
    public HttpHeaders build(String email) {
        headers.set(requestProperty.getEmail(), email);
        return headers;
    }
}

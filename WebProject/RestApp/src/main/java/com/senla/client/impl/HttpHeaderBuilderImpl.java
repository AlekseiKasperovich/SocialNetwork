package com.senla.client.impl;

import com.senla.api.dto.сonstants.Constants;
import com.senla.client.HttpHeaderBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

/**
 *
 * @author Aliaksei Kaspiarovich
 */
@Service
@RequiredArgsConstructor
public class HttpHeaderBuilderImpl extends CurrentUserService
        implements HttpHeaderBuilder {

    private final HttpHeaders headers;

    @Override
    public HttpHeaders build() {
        headers.set(Constants.EMAIL_HEADER, getCurrentUserEmail());
        return headers;
    }

    @Override
    public HttpHeaders build(String email) {
        headers.set(Constants.EMAIL_HEADER, email);
        return headers;
    }
}

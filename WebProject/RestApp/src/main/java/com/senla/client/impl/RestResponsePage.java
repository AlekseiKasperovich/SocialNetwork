package com.senla.client.impl;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Aliaksei Kaspiarovich
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class RestResponsePage<T> extends PageImpl<T> {

    protected static final String CONTENT = "content";
    protected static final String NUMBER = "number";
    protected static final String SIZE = "size";
    protected static final String TOTAL_ELEMENTS = "totalElements";
    protected static final String PAGEABLE = "pageable";
    protected static final String LAST = "last";
    protected static final String TOTAL_PAGES = "totalPages";
    protected static final String SORT = "sort";
    protected static final String FIRST = "first";
    protected static final String NUMBER_OF_ELEMENTS = "numberOfElements";

    @JsonCreator(mode = Mode.PROPERTIES)
    public RestResponsePage(@JsonProperty(CONTENT) List<T> content,
                            @JsonProperty(NUMBER) int number,
                            @JsonProperty(SIZE) int size,
                            @JsonProperty(TOTAL_ELEMENTS) Long totalElements,
                            @JsonProperty(PAGEABLE) JsonNode pageable,
                            @JsonProperty(LAST) boolean last,
                            @JsonProperty(TOTAL_PAGES) int totalPages,
                            @JsonProperty(SORT) JsonNode sort,
                            @JsonProperty(FIRST) boolean first,
                            @JsonProperty(NUMBER_OF_ELEMENTS) int numberOfElements) {

        super(content, PageRequest.of(number, size), totalElements);
    }

    public RestResponsePage(List<T> content, Pageable pageable, long total) {
        super(content, pageable, total);
    }

    public RestResponsePage(List<T> content) {
        super(content);
    }

    public RestResponsePage() {
        super(new ArrayList<>());
    }
}

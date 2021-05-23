package com.bluecc.ws.charts;

import com.fasterxml.jackson.annotation.JsonProperty;

public final class GenericResponse {
    public static final String SUCCESS = "success";

    private final String result;
    private final String from;
    private final Object data;

    public GenericResponse(String result, String from, Object data) {
        this.result = result;
        this.from = from;
        this.data = data;
    }

    @JsonProperty
    public Object getData() {
        return data;
    }

    @JsonProperty
    public String result() {
        return result;
    }

    @JsonProperty
    public String from() {
        return from;
    }
}

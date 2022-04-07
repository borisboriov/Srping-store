package com.soskin.store.springstore.api.dto;


import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "StringResponse model")
public class StringResponse {

    @Schema(description = "Value", required = true, example = "111")
    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public StringResponse(String value) {
        this.value = value;
    }

    public StringResponse() {
    }
}

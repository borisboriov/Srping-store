package com.soskin.store.springstore.api.core;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "ProfileDto model")
public class ProfileDto {

    @Schema(description = "Username", required = true, example = "Avi")
    private String username;

    public ProfileDto(String username) {
        this.username = username;
    }

    public ProfileDto() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}

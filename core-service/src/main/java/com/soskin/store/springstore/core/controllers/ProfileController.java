package com.soskin.store.springstore.core.controllers;

import com.soskin.store.springstore.api.core.ProfileDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/profile")
@Tag(name = "Profiles", description = "methods for work with profiles")
public class ProfileController {

    @Operation(
            summary = "Request to get current user info by username",
            responses = {
                    @ApiResponse(
                            description = "Success response", responseCode = "200"
                    )

            }
    )
    @GetMapping
    public ProfileDto getCurrentUserInfo(@RequestHeader @Parameter(description = "Username", required = true) String username) {
        return new ProfileDto(username);
    }
}

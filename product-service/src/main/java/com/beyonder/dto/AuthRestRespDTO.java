package com.beyonder.dto;

import org.eclipse.microprofile.openapi.annotations.media.Schema;


public class AuthRestRespDTO {
    @Schema(title = "data", example = "Rest from auth service", description = "data from auth-service")
    public String data;
    public String status;
}

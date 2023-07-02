package com.beyonder;

import jakarta.ws.rs.Path;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;


public class AuthDTO {
    @Schema(title = "data", example = "Rest from auth service", description = "data from auth-service")
    public String data;
    public String status;
}

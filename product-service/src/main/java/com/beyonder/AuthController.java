package com.beyonder;

import com.beyonder.proxy.AuthRest;
import io.vertx.core.json.JsonObject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@Path("/v1/auth")
@Tag(name = "Auth Controller", description = "Authentication")
public class AuthController {

    @RestClient
    AuthRest authRest;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response helloAuthService() {
        if(true) {
            AuthDTO authDTO = new AuthDTO();
            authDTO.data = authRest.helloAuthService();
            return Response.ok().entity(authDTO).build();
        } else {
            return null;
        }

    }
}

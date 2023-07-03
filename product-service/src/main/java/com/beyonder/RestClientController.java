package com.beyonder;

import com.beyonder.dto.AuthRestRespDTO;
import com.beyonder.proxy.AuthRest;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@Path("/v1/rest-client")
@Tag(name = "Rest Client Controller", description = "Communication to outside via rest")
public class RestClientController {

    @RestClient
    AuthRest authRest;

    @GET
    @Path("/auth-service")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Communicate to auth-service", description = "Get the string data")
    public Response helloAuthService() {
        AuthRestRespDTO authRestRespDTO = new AuthRestRespDTO();
        authRestRespDTO.data = authRest.helloAuthService();
        return Response.ok().entity(authRestRespDTO).build();

    }
}

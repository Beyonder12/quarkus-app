package com.beyonder.proxy;

import com.beyonder.AuthDTO;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.util.List;

@RegisterRestClient(baseUri = "http://localhost:1001/auth-service/api")
public interface AuthRest {

    @GET
    @Path("/v1/hello")
    String helloAuthService();
}

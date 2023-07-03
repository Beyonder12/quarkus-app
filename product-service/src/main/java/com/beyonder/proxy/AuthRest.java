package com.beyonder.proxy;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

//@RegisterRestClient(baseUri = "http://auth-service:2001/auth-service/api")
@RegisterRestClient(configKey = "auth-service")
public interface AuthRest {

    @GET
    @Path("/v1/hello")
    String helloAuthService();
}

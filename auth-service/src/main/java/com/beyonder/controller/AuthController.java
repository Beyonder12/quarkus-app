package com.beyonder.controller;

import com.beyonder.dto.request.RegisterLoginReqDTO;
import com.beyonder.service.AuthService;
import io.vertx.core.json.JsonObject;
import jakarta.inject.Inject;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

import java.security.InvalidKeyException;
import java.security.spec.InvalidKeySpecException;
import java.util.Set;

@Path("/v1/auths")
public class AuthController {

    @Inject
    Validator validator;

    @Inject
    AuthService authService;

    @POST
    @Path("/registrations")
    public Response addUser(RegisterLoginReqDTO user) throws InvalidKeySpecException {
        Set<ConstraintViolation<RegisterLoginReqDTO>> validates = validator.validate(user);

        if(validates.isEmpty()) {
            return authService.addUser(user);
        } else {
            JsonObject response = new JsonObject();
            for (ConstraintViolation<RegisterLoginReqDTO> violation : validates) {
                response.put(violation.getPropertyPath().toString(), violation.getMessage());
            }
            return Response.status(Response.Status.BAD_REQUEST).entity(response).build();
        }
    }

    @POST
    @Path("/signings")
    public Response login(RegisterLoginReqDTO loginReq) throws InvalidKeySpecException, InvalidKeyException {
        if(loginReq.getEmail() == null) loginReq.setEmail("fajri@gmail.com");
        Set<ConstraintViolation<RegisterLoginReqDTO>> validates = validator.validate(loginReq);


        if(validates.isEmpty()) {
            return authService.login(loginReq);
        } else {
            JsonObject response = new JsonObject();
            for (ConstraintViolation<RegisterLoginReqDTO> violation : validates) {
                response.put(violation.getPropertyPath().toString(), violation.getMessage());
            }
            return Response.status(Response.Status.BAD_REQUEST).entity(response).build();
        }

    }
}

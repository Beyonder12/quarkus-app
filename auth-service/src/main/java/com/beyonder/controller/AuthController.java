package com.beyonder.controller;

import com.beyonder.dto.AddUserReqDTO;
import com.beyonder.model.User;
import com.beyonder.service.AuthService;
import io.vertx.core.json.JsonObject;
import jakarta.enterprise.inject.build.compatible.spi.Validation;
import jakarta.inject.Inject;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Valid;
import jakarta.validation.Validator;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

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
    public Response addUser(AddUserReqDTO user) throws InvalidKeySpecException {
        Set<ConstraintViolation<AddUserReqDTO>> validate = validator.validate(user);

        if(validate.isEmpty()) {
            return authService.addUser(user);
        } else {
            JsonObject response = new JsonObject();
            for (ConstraintViolation<AddUserReqDTO> violation : validate) {
                response.put(violation.getPropertyPath().toString(), violation.getMessage());
            }
            return Response.status(Response.Status.BAD_REQUEST).entity(response).build();
        }

    }
}

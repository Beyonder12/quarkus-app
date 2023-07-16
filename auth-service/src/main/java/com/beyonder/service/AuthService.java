package com.beyonder.service;

import com.beyonder.dto.AddUserReqDTO;
import com.beyonder.model.User;
import com.beyonder.repository.AuthRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.vertx.core.json.JsonObject;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;

import java.security.spec.InvalidKeySpecException;

@ApplicationScoped
public class AuthService {

    @Inject
    AuthRepository authRepository;

    @Inject
    PasswordService passwordService;
    @Transactional
    public Response addUser(AddUserReqDTO req) throws InvalidKeySpecException {
        ObjectMapper om = new ObjectMapper();
        User user = om.convertValue(req, User.class);

        user.setPassword(passwordService.hashPassword(req.getPassword()));

        authRepository.persist(user);
        return Response.ok(user).build();
    }
}

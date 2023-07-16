package com.beyonder.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.beyonder.dto.request.RegisterLoginReqDTO;
import com.beyonder.dto.response.LoginRespDTO;
import com.beyonder.model.User;
import com.beyonder.repository.AuthRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;

import java.security.InvalidKeyException;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Date;
import java.util.Optional;

@ApplicationScoped
public class AuthService {

    @Inject
    AuthRepository authRepository;

    @Inject
    PasswordService passwordService;
    @Transactional
    public Response addUser(RegisterLoginReqDTO req) throws InvalidKeySpecException {
        ObjectMapper om = new ObjectMapper();
        User user = om.convertValue(req, User.class);

        user.setPassword(passwordService.hashPassword(req.getPassword()));

        authRepository.persist(user);
        return Response.ok(user).build();
    }

    public Response login(RegisterLoginReqDTO loginReq) throws InvalidKeySpecException, InvalidKeyException {
        String username = loginReq.getUsername();
        String password = loginReq.getPassword();


        User user = authRepository.findByUsername(username)
                .orElseThrow(() -> new WebApplicationException("User not found", 404));
        if(!passwordService.checkPassword(user.getPassword(), password)) {
            throw new WebApplicationException("wrong credentials");
        }

        // Create JWT token
        Algorithm algorithm = Algorithm.HMAC256("secret");  // Use your own secret. Be sure it is safe and unguessable.
        String token = JWT.create()
                .withIssuer("quarkus-app")
                .withClaim("username", username) // Add custom claims as needed
                .withExpiresAt(new Date(System.currentTimeMillis() + 60*60*1000))    // You might want to add an expiration date to your token
                .sign(algorithm);

        // Create a response that includes the JWT token
        LoginRespDTO resp = new LoginRespDTO();
        resp.setAccessToken(token);
        resp.setUsername(user.getUsername());

        return Response.ok().entity(resp).build();
    }
}

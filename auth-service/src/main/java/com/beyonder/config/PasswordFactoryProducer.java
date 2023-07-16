package com.beyonder.config;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import org.wildfly.security.password.PasswordFactory;

import java.security.NoSuchAlgorithmException;

@ApplicationScoped
public class PasswordFactoryProducer {

    @Produces
    public PasswordFactory createPasswordFactory() throws NoSuchAlgorithmException {
        return PasswordFactory.getInstance("bcrypt");
    }
}
